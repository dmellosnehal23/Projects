from django.contrib.auth.models import User
from django.core.context_processors import csrf
from django.shortcuts import render_to_response
from django.template.loader import get_template
from django.db import models
from django.template import Context
from classroom.models import Site
import urllib2, httplib2
import requests
import sqlite3 as lite
import json
import simplejson
from django.contrib.auth.forms import UserCreationForm
from django.views.decorators.csrf import requires_csrf_token, csrf_exempt
from views import show_url
#authentication modules
from django.http import HttpResponseRedirect
from django.contrib import auth
from django.core.context_processors import csrf

default_mooc = None

con=lite.connect('sqlite.db')
def connections():
    url=None
    with con:
        cur = con.cursor()    
        cur.execute("SELECT domain_name FROM classroom_site where default_url=1")
        rows = cur.fetchone()
        print rows
        for row in rows:
            url=row
            print row
        print "url is %s"%url
    return url

def announcement(request):
    return render_to_response('announcement.html')

def add_announcement_show_page(request):
    courseID=request.POST.get('courseID')
    print "courseID is %s "%courseID
    return render_to_response('announcement_add.html',{"courseID":courseID})

def add_announcement(request):
    new_url=connections()
    url = "http://%s/announcements" % (new_url)
    courseID=request.POST.get('courseID')
    announcement_title = request.POST.get('announcement_title')
    announcement_description = request.POST.get('announcement_description')
    data = json.dumps({'courseId':courseID,'title':announcement_title,'description':announcement_description}) 
    r=requests.post(url,data)
    return render_to_response('home.html',{'name':"announcement successfully added"})


def update_announcement(request):
    default_mooc=show_url()
    old_email = request.session['email']
    #form=User.objects.update(email, email, password)
    form=User.objects.get_by_natural_key(old_email)
    #written email twice previous one has to be deleted and fetched from session so that email can also change
    announcement_id=request.POST.get('announcement_id')
    announcement_title = request.POST.get('announcement_title')
    announcement_description = request.POST.get('announcement_description')
    id=announcement_id.split(':')
    url = "http://%s/announcement/update/%s" % (default_mooc.domain_name,id[1])
    data = json.dumps({'announcement_title':announcement_title,'announcement_description':announcement_description})
    r=requests.put(url, data)
    return render_to_response('home.html',{'name':"Announcement Successfully updated"})

def list_announcement_relatedto_course(request):
    new_url=connections()
    url = "http://%s/announcement/list" % (new_url) 
    r=requests.get(url)
    courseID=request.POST.get('courseID')
    return render_to_response('announcement_course.html',{'data':r.json(),"courseID":courseID})

def list_announcement(request):
    new_url=connections()
    url = "http://%s/announcement/list" % (new_url) 
    r=requests.get(url)
    return render_to_response('announcement_list.html',{'data':r.json()})

def update_announcement_show_page(request):
    announcement_id = request.POST.get('announcement_id')
    announcement_title = request.POST['announcement_title']
    announcement_description = request.POST.get('announcement_description')
    data = json.dumps({'announcement_id':announcement_id,'announcement_title':announcement_title,'announcement_description':announcement_description})
#     new_url=connections()
#     url = "http://%s/announcement/update/%s" % (new_url,announcement_id)
#     r=requests.put(url,data)
    print data
    return render_to_response('announcement_edit.html',{'announcement_id':announcement_id,'announcement_title':announcement_title,'announcement_description':announcement_description})

def delete_announcement(request):
    new_url=connections()
    announcement_id=request.POST.get('announcement_id')
    url = "http://%s/announcement/%s" % (new_url,announcement_id)
    r=requests.delete(url)
    return render_to_response('index.html',{'name':"Announcement deleted successfully"})
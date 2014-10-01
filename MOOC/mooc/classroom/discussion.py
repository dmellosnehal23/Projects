from django.http import HttpResponse
from django.shortcuts import render_to_response
from django.template.loader import get_template
from django.db import models
from django.template import Context
from classroom.models import Site
import sqlite3 as lite
import urllib2
#import simplejson
import json
import sys
import httplib2, urllib
from django.contrib import auth
from django.core.context_processors import csrf
from django.contrib.auth.forms import UserCreationForm
from django.views.decorators.csrf import requires_csrf_token, csrf_exempt
import requests

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

def add_discussion_page(request):
    return render_to_response('discussions_add.html')

def add_discussion(request):
    new_url=connections()
    url = "http://%s/discussions" % (new_url)
    discussion_title = request.POST.get('discussion_title')
    if request.session['email'] is not None:
        email=request.session['email']
    data = json.dumps({'title':discussion_title,'created_by':email}) 
    print data
    r=requests.post(url,data)
    return render_to_response('home.html',{'name':r})

def delete_discussion(request):
    discussion_id = request.session['discussion_id']
    new_url=connections()
    url = "http://%s/discussion/%s" % (new_url,discussion_id) 
    r=requests.delete(url)
    return render_to_response('home.html',{'name':r})

def list_discussion(request):
    new_url=connections()
    url = "http://%s/discussion/list" % (new_url) 
    r=requests.get(url)
    return render_to_response('discussions_list.html',{'data':r.json()})

def discussion(request):
    return render_to_response('discussion.html')

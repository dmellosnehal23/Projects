# Create your views here.
from django.http import HttpResponse
from django.shortcuts import render_to_response
from django.template.loader import get_template
from django.db import models
from django.template import Context
from classroom.models import Site
import sqlite3 as lite
import urllib2
import simplejson
import json
import sys
import httplib2, urllib
from django.contrib import auth
from django.core.context_processors import csrf
from django.contrib.auth.forms import UserCreationForm
from django.views.decorators.csrf import requires_csrf_token, csrf_exempt
import requests
from users import get_user
from django.contrib.auth.models import User

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

def courses(request):
    return render_to_response('courses.html')

def add_course_show_page(request):
    new_url=connections() 
    url = "http://%s/category/list" % (new_url)
    r=requests.get(url)
    data=r.json()
    print data
    return render_to_response('course_add.html',{'data':data})



def enroll_in_course(request):
    print "inside enroll course"
    new_url=connections()
    courseId = request.POST.get('courseid')
    url = "http://%s/course/enroll" % (new_url)
    email = request.session['email']
    print email
    data=json.dumps({"email":email,"courseId":courseId})
    print "Data is %s" %data
    r=requests.put(url,data)
    if r.status_code==200 or r.status_code==201:
        return render_to_response('home.html',{'name':"successfully enrolled"})
    else:
        return render_to_response('home.html',{'error':"Internal server error"})
    

def add_course(request):
    new_url=connections()
    url = "http://%s/course" % (new_url)
    email = request.session['email']
    category=request.POST.get('category')
    mylist = category.split(':')
    number=len(mylist)
    if number==1:
        original_category=mylist[0]
    else:
        original_category=mylist[1]
    course_title = request.POST.get('course_title')
    course_section = request.POST.get('course_section')
    dept = request.POST.get('dept')
    term = request.POST.get('term')
    year = request.POST.get('year')
    days=request.POST.get('days')
    time=request.POST.get('time')
    instr_name = request.POST.get('instr_name')
    instr_email = request.POST.get('instr_email')
    description = request.POST.get('description')
    attachment = request.POST.get('attachment')
    print days
    print time
    data = json.dumps({"email":email,"category": original_category,"title": course_title,"section": course_section,"dept": dept,"term": term, "year": year,"instructor": [{"name": instr_name, "email": instr_email}],"days": days,"hours": time,"Description": description,"attachment": attachment,"version": "1"}) 
    #print data
    r=requests.post(url,data)
    print r.status_code
    if r.status_code==200 or r.status_code==201:
        return render_to_response('home.html',{'name':"successfully updated"})
    else:
        return render_to_response('home.html',{'error':"some error occured"})

def get_course(request,course_id):
    new_url=connections()
    
    url = "http://%s/course/%s" % (new_url,course_id)
    #email = request.POST.get('email','')
    #course_id=request.POST.get('course_id','') 
    r=requests.get(url)
    return r.json()

def list_course(request):
    new_url=connections()
    url = "http://%s/course/list" % (new_url)
    #email = request.POST.get('email','')
    #course_id=request.POST.get('course_id','') 
    r=requests.get(url)
    return render_to_response('course_list.html',{'data':r.json()})

def edit_course(request):
    email = request.session['email']
    courseID=request.POST.get('courseID')
    category=request.POST.get('category')
    course_title = request.POST.get('course_title')
    course_section = request.POST.get('course_section')
    dept = request.POST.get('dept')
    term = request.POST.get('term')
    year = request.POST.get('year')
    #days=request.POST.get('days')
    days=['Monday', 'Wednesday', 'Friday']
    print "Days are %s"%days
    #time=request.POST.get('hours')
    time= ['8:00AM', '9:15:AM']
    print "time are %s"%time
    instr_name = request.POST.get('instr_name')
    instr_email = request.POST.get('instr_email')
    description = request.POST.get('description')
    attachment = request.POST.get('attachment')
    data = json.dumps({"email":email,"category": category,"title": course_title,"section": course_section,"dept": dept,"term": term, "year": year,"instructor": [{"name": instr_name, "email": instr_email}],"days": days,"hours": time,"Description": description,"attachment": attachment,"version": "1"})
    print data
#     new_url=connections()
#     url = "http://%s/course/update/%s" % (new_url,courseID)
#     r=requests.put(url,data)
    return render_to_response('course_update.html',{"courseID":courseID,"email":email,"category": category,"title": course_title,"section": course_section,"dept": dept,"term": term, "year": year,"instructor": [{"name": instr_name, "email": instr_email}],"days": days,"hours": time,"Description": description,"attachment": attachment,"version": "1"})

def owned_course(request):
    new_url=connections()
    url = "http://%s/course/list" % (new_url)
    email=request.session['email']
    general_list=get_user(request, email)
    print general_list
    owned_list=general_list.get('own')
    print owned_list
    if owned_list is None:
        return render_to_response('home.html',{'error':"sorry you had not uploaded any course yet"})
    data={}
    combined_data={}
    lists=[]
    for list in owned_list:
        print "this is list %s"%list
        data=get_course(request, list)
        print data
        old_data={"courseID":list}
        # appending courseId
        new_data = dict(data.items() + old_data.items())
        print "newly listed and updated data %s"%new_data
        lists.append(new_data)

        #combined_data.update(data)
    #print data
    print "New data %s"%lists
    return render_to_response('course_edit.html',{'data':lists})

def update_course(request):
    new_url=connections()
    course_id=request.POST.get('courseID')
    url = "http://%s/course/update/%s" % (new_url,course_id)
    email = request.session['email']
    category=request.POST.get('category')
    course_title = request.POST.get('course_title')
    course_section = request.POST.get('course_section')
    dept = request.POST.get('dept')
    term = request.POST.get('term')
    year = request.POST.get('year')
    days=request.POST.get('days')
    #days=['Monday', 'Wednesday', 'Friday']
    print "Days are %s"%days
    time=request.POST.get('hours')
    #time= ['8:00AM', '9:15:AM']
    print "time are %s"%time
    instr_name = request.POST.get('instr_name')
    instr_email = request.POST.get('instr_email')
    description = request.POST.get('description')
    attachment = request.POST.get('attachment')
    data = json.dumps({"email":email,"category": category,"title": course_title,"section": course_section,"dept": dept,"term": term, "year": year,"instructor": [{"name": instr_name, "email": instr_email}],"days": days,"hours": time,"Description": description,"attachment": attachment,"version": "1"})
    print data     
    r=requests.put(url,data)
    return render_to_response('index.html',{'name':r})

def delete_course(request):
    new_url=connections()
    course_id="518cbf961d41c80d6a53f755"
    url = "http://%s/course/%s" % (new_url,course_id)
    r=requests.delete(url)
    return render_to_response('index.html',{'name':r})
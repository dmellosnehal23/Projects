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
from django.contrib.auth.models import User
from django.core.context_processors import csrf
from django.http import HttpResponseRedirect
from django.contrib.auth.forms import UserCreationForm
from django.views.decorators.csrf import requires_csrf_token, csrf_exempt
import requests
from bottle import response, request
from django.contrib.formtools.tests.wizard.wizardtests.forms import UserForm

con=lite.connect('sqlite.db')

def hello(request):
    name='meet'
    html='<html><body>Hi %s how are you!!!</body></html>' %name
    return HttpResponse(html)

def hello_template(request):
    name='meet mehta'
    t=get_template('hello.html')
    url=connections()
    html=t.render(Context({'name':name}))
    return HttpResponse(html)

def hello_template_simple(request):
    name='meet'
    #url = "http://localhost:8080/documents/%s" % name
    #req = urllib2.Request(url)
    #opener = urllib2.build_opener()
    #f=opener.open(req)
    #json=simplejson.load(f)
    a=Site.objects.all()
    #json.get('filename')
    return render_to_response('hello.html',{'name':a})

def user(request,user_id='default user'):
    name=user_id
    new_url=connections()
    print new_url
    url = "http://%s/documents/%s" % (new_url,name)
    #req = urllib2.Request(url)
    #res = urllib2.urlopen(req)
    #content = res.read()
    #r = requests.get(url)
    
    h = httplib2.Http(".cache")
    resp, content = h.request(url)
    return render_to_response('hello.html',{'name':resp})



default_mooc = None
def show_group(request):
    global default_mooc
    latest_mooc_list = Site.objects.all()
    for mooc in latest_mooc_list :
        if mooc.default_url :
            print 'mooc Primary URL ===>', mooc.domain_name
            default_mooc = mooc
            break
    print 'default_mooc Primary URL ===>', default_mooc.display_name
    return render_to_response('index.html',{'name':default_mooc.display_name+" "+default_mooc.domain_name})

def show_url():
    latest_mooc_list = Site.objects.all()
    for mooc in latest_mooc_list :
        if mooc.default_url :
            print 'mooc Primary URL ===>', mooc.domain_name
            default_mooc = mooc
            break
    print 'default_mooc Primary URL ===>', default_mooc.display_name
    return default_mooc

     
@csrf_exempt
def create_user(request):
    email = request.POST.get('email','')
    print "this is my email %s" %email
    new_url=connections()
    url = "http://%s/user" % (new_url)
#     h = httplib2.Http()
#     json_data={'file_id': 1, 'filename': "meet" , 'links_to' : "meet_link"}
#     resp, content = h.request(url,method="POST",body=email)
    data = json.dumps({'email':email}) 
    r = requests.post(url, data)
    json_data=r.json()
    request.session['email']=email
    return render_to_response('index.html',{'name':json_data})

def get_user(request,email):
    new_url=connections()
    url = "http://%s/user/%s" % (new_url,email)
    r=requests.get(url)
    json_data=r.json()
    #print json_data
    #status=r.status_code
    #print "status is %s"%status
    
    if 'email' not in request.session:
        request.session['email']="default"
    else:
        email=request.session['email']
    return render_to_response('index.html',{'name':email})

def update_user(request,email):
    new_url=connections()
    url = "http://%s/user/update/%s" % (new_url,email)
    data = json.dumps({'email':email})
    r=requests.put(url, data)
    json_data=r.json()
    return render_to_response('index.html',{'name':json_data})

def delete_user(request,email):
    new_url=connections()
    url = "http://%s/user/%s" % (new_url,email)
    r=requests.delete(url)
    json_data=r.json()
    return render_to_response('index.html',{'name':json_data})

def enroll_course(request):
    new_url=connections()
    url = "http://%s/course/enroll" % (new_url)
    #email = request.POST.get('email','')
    #course_id=request.POST.get('course_id','')
    email="meet"
    course_id="1"
    data = json.dumps({'email':email, 'courseId':course_id}) 
    r=requests.put(url,data)
    return render_to_response('index.html',{'name':r})

def drop_course(request):
    new_url=connections()
    url = "http://%s/course/drop" % (new_url)
    #email = request.POST.get('email','')
    #course_id=request.POST.get('course_id','')
    email="meet"
    course_id="1"
    data = json.dumps({'email':email, 'courseId':course_id}) 
    r=requests.put(url,data)
    return render_to_response('index.html',{'name':r})

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

#
#------------------LOGINS
#
def login(request):
    return render_to_response('login.html')

def auth_view(request):
    email=request.POST.get('email', '')
    password=request.POST.get('password', '')
    user=auth.authenticate(email=email,password=password)
    
    if user is not None:
        auth.login(request, user)
        return HttpResponseRedirect('/accounts/loggedin')
    else:
        return HttpResponseRedirect('/accounts/invalid')
    
def loggedin(request):
    return render_to_response('index.html',{'name':request.user.email})

def invalid_login(request):
    return render_to_response('index.html',{'name':"invalid login"})

def logout(request):
    auth.logout(request)
    return render_to_response('index.html',{'name':"loggedout"})

def signin(requeset):
    email = request.POST['email']
    password = request.POST['password']
    user = auth.authenticate(email=email, password=password)
    print user
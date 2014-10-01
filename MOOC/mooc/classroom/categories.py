# Create your views here.
from django.http import HttpResponse
from django.shortcuts import render_to_response
from django.template.loader import get_template
from django.db import models
from django.template import Context
from classroom.models import Site
import sqlite3 as lite
#import urllib2
#import simplejson
import json
import sys
#import httplib2, urllib
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

def categories(request):
    return render_to_response('categories.html')

def show_category_add_page(request):
    return render_to_response('category_add.html')

def add_category(request):
    name = request.POST.get('name')
    description = request.POST.get('description')
    new_url=connections()
    url = "http://%s/category" % (new_url)
    #email = request.POST.get('email','')
    #course_id=request.POST.get('course_id','')
    #course_id="1"
    data = json.dumps({'name':name,'description':description}) 
    r=requests.post(url,data)
    if r.status_code==200 or r.status_code==201:
        return render_to_response('home.html',{'name':"your category added"})
    elif r.status_code==409:
        return render_to_response('categories_add.html',{'error':"Already exists Category"})
    else:
        return render_to_response('categories_add.html',{'error':"Internal server error pls try again"})
    

def get_category(request):
    new_url=connections()
    category_id="RangersCategory:34820c5e-9da9-4ff9-87f3-8ac6354cffd1" 
    url = "http://%s/category/%s" % (new_url,category_id)
    r=requests.get(url)
    return render_to_response('home.html',{'name':r})

def list_category(request):
    new_url=connections() 
    url = "http://%s/category/list" % (new_url)
    r=requests.get(url)
    return render_to_response('category_list.html',{'name':r.json()})
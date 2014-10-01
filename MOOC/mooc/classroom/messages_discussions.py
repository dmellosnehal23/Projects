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

def add_message_page(request):
    discussion_id=email=request.POST.get('discussion_id', '')
    discussion_title=email=request.POST.get('discussion_title', '')
    return render_to_response('message_add.html',{"discussion_id":discussion_id,"discussion_title":discussion_title})
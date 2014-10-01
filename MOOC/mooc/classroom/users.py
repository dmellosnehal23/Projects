from django.contrib.auth.models import User
#from django.core.context_processors import csrf
from django.shortcuts import render_to_response
import requests
import json
#from django.contrib.auth.forms import UserCreationForm
from django.views.decorators.csrf import csrf_exempt
from views import show_url
#authentication modules
from django.http import HttpResponseRedirect
from django.contrib import auth
from django.core.context_processors import csrf

default_mooc = None

def register(request):
    return render_to_response('register.html')

@csrf_exempt
def register_user(request):
    default_mooc=show_url()
    if request.method =='POST':
        print "inside register post method"
        #form=UserForm(request.POST)
        #form = UserCreationForm(request.POST)
        #print form.error_messages
#         if form.is_valid():
#             print "inside validated form and going to save"
#             form.save()
#             return HttpResponseRedirect('/register/')
#         else:
#             form=UserCreationForm()
#         
#         args={}
#         args.update(csrf(request))
#         args['form']=form
#         return render_to_response('index.html',{'name':args})
        #form=UserCreationForm(request.POST)
        
            #form.save()
    #username = request.POST.get('email')
    
    errors = []
    if request.method == 'POST':
        if not request.POST.get('password1', ''):
            errors.append('Enter a password.')
        if request.POST.get('pasword1', '') != request.POST.get('pasword2', '') :
            errors.append('password mismatch')
        if request.POST.get('email') and '@' not in request.POST['email']:
            errors.append('Enter a valid e-mail address.')
        if not errors:
            email = request.POST.get('email')
            password = request.POST.get('password1')
            first_name = request.POST.get('first_name')
            last_name = request.POST.get('last_name')
            try:
                form=User.objects.create_user(email, email, password)
                form.first_name = first_name
                form.last_name = last_name
                form.save()
            except:
                return render_to_response('login.html',{'error':"username already exist"})
            #return HttpResponseRedirect('/contact/thanks/')
        else:
            return render_to_response('register.html',{'error':errors})
    
    url = "http://%s/user" % (default_mooc.domain_name)
    data = json.dumps({'email':email}) 
    r = requests.post(url, data)
    print r
    json_data=r.json()
    request.session['email']=email
    if r.status_code==200 or r.status_code==201:
        return render_to_response('login.html',{'error':"pls login with the email you already registered"})
    elif r.status_code==409:
        return render_to_response('home.html',{'error':"username already exist"})
    
    #return HttpResponseRedirect('/register/')

def login(request):
    c={}
    c=csrf(request)
    return render_to_response('login.html',c)

def account_settings(request):
    return render_to_response('account_settings.html')

def auth_view(request):
    print "inside auth view"
    email=request.POST.get('email', '')
    password=request.POST.get('password', '')
    #request.session.flush()
    request.session['email']=email
    print email
    print password
    user=auth.authenticate(username=email,password=password)
    print user
    if user is not None:
        auth.login(request, user)
        return HttpResponseRedirect('/classroom/accounts/loggedin')
    else:
        return render_to_response('login.html',{'error':"username/password mismatch"})

def loggedin(request):
    return render_to_response('home.html',{'name':"welcome "+request.user.first_name})

def logout(request):
    auth.logout(request)
    return render_to_response('login.html',{'error':"you successfully loggedout"})

def update_account(request):
    email=request.session['email']
    form=User.objects.get_by_natural_key(email)
    return render_to_response('update_account.html',{"username":form.username,"email":form.email,"password":form.password,"first_name":form.first_name,"last_name":form.last_name})

def update_user(request):
    default_mooc=show_url()
    old_email = request.session['email']
    #form=User.objects.update(email, email, password)
    form=User.objects.get_by_natural_key(old_email)
    #written email twice previous one has to be deleted and fetched from session so that email can also change
    new_email = request.POST.get('email')
    password = request.POST.get('password1')
    first_name = request.POST.get('first_name')
    last_name = request.POST.get('last_name')
    form.first_name = first_name
    form.last_name = last_name
    form.email=new_email
    form.password=password
    form.username=new_email
    form.save()
    #url = "http://%s/user/update/%s" % (default_mooc.domain_name,email)
    #data = json.dumps({'email':email})
    #r=requests.put(url, data)
    #json_data=r.json()
    return render_to_response('home.html',{'name':"Values Successfully updated"})

def get_user(request,email):
    default_mooc=show_url()
    url = "http://%s/user/%s" % (default_mooc.domain_name,email)
    r=requests.get(url)
    json_data=r.json()
    #print json_data
    #status=r.status_code
    #print "status is %s"%status
    if 'email' not in request.session['email']:
        request.session['email']="s@gmail.com" #passing default email id
    else:
        email=request.session['email']
    return json_data

def get_enrolled_course(request):
    email=request.session['email']
    if 'email' not in request.session['email']:
        request.session['email']="s@gmail.com"
    else:
        email=request.session['email']
    print email
    user_data=get_user(request, email)
    print user_data
    enrolled_data=user_data['enrolled']
    print enrolled_data
    
    default_mooc=show_url()
    data={}
    for e in enrolled_data:
        url = "http://%s/course/%s" % (default_mooc.domain_name,e)
        r=requests.get(url)
        print r.json()
        single_data=r.json()
        data = dict(data.items() + single_data.items())
    return render_to_response('show_enrolled_course.html',{'data':data})

def delete_user(request):
    if 'email' in request.session:
        email = request.session['email']
        form=User.objects.get_by_natural_key(email)
        form.delete()
        default_mooc=show_url()
        url="http://%s/user/%s" %(default_mooc.domain_name,email)
        r=requests.delete(url)
        if r.status_code==200 or r.status_code==201:
            return render_to_response('login.html',{'error':"successfully deleted"})
        else:
            return render_to_response('home.html',{'error':"some error occured"})
        return render_to_response('login.html',{'error':"your account deleted successfully pls register again"})
    else:
        email="nothing"
        return render_to_response('login.html',{'error':"sorry some error occured of session time out"})
    print email
    #form=User.objects.get_by_natural_key(email)
    #form=User.objects.get(pk=email)
    #form.delete()
    return render_to_response('login.html',{'error':"your account deleted successfully pls register again"})
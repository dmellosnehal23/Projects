from django.conf.urls import patterns, include, url

# Uncomment the next two lines to enable the admin:
from classroom import views,users,course,categories,announcement,discussion

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'mysite.views.home', name='home'),
    # url(r'^mysite/', include('mysite.foo.urls')),
    
    #homepage
    #url(r'^$', views.index, name='index'),
    
    #related to users
    url(r'^register/$', users.register),
    url(r'^register_user/$', users.register_user),
    
    
    url(r'^hello/$', views.hello),
    url(r'^hello_template/$', 'classroom.views.hello_template'),
    url(r'^hello_template_simple/$', 'classroom.views.hello_template_simple'),
    url(r'^user/(?P<user_id>.+)/$', views.user,name='something'),
    url(r'^user/$', views.user),
    url(r'^create_user/$', views.create_user),
    
    
    
    url(r'^show_group/$', views.show_group),
    
    url(r'^signin/$', views.signin),
    
    url(r'^enroll_course/$', views.enroll_course),
    url(r'^drop_course/$', views.drop_course),
    

    
    url(r'^categories/$', categories.categories),
    url(r'^show_category_add_page/$', categories.show_category_add_page),
    url(r'^add_category/$', categories.add_category),
    url(r'^get_category/$', categories.get_category),
    url(r'^list_category/$', categories.list_category),
    
    url(r'^courses/$', course.courses),
    url(r'^add_course_show_page/$', course.add_course_show_page),
    url(r'^add_course/$', course.add_course),
    url(r'^get_course/$', course.get_course),
    url(r'^edit_course/$', course.edit_course),
    url(r'^owned_course/$', course.owned_course),
    url(r'^enroll_in_course/$', course.enroll_in_course),
    url(r'^list_course/$', course.list_course),
    url(r'^update_course/$', course.update_course),
    url(r'^delete_course/$', course.delete_course),
    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    #url(r'^homepage_loggedin/$', users.homepage_loggedin),
    url(r'^accounts/login/$', users.login),
    url(r'^accounts/auth/$', users.auth_view),
    url(r'^accounts/logout/$', users.logout),
    url(r'^accounts/loggedin/$', users.loggedin),
    #url(r'^accounts/invalid_login/$', views.invalid_login),
    url(r'^get_user/(?P<email>.+)/$', users.get_user),
    url(r'^accounts/account_settings/$', users.account_settings),
    url(r'^accounts/update_account/$', users.update_account),
    url(r'^accounts/update_user/$', users.update_user),
    url(r'^accounts/delete_user/$', users.delete_user),
    url(r'^accounts/get_enrolled_course/$', users.get_enrolled_course),
    
    
    url(r'^courses/update_announcement_show_page/$', announcement.update_announcement_show_page),
    url(r'^courses/list_announcement_relatedto_course/$', announcement.list_announcement_relatedto_course),
    url(r'^courses/add_announcement_show_page/$', announcement.add_announcement_show_page),
    url(r'^courses/announcement/$', announcement.announcement),
    url(r'^courses/add_announcement/$', announcement.add_announcement),
    url(r'^courses/edit_announcement/$', announcement.update_announcement),
    url(r'^courses/list_announcement/$', announcement.list_announcement),
    url(r'^courses/delete_announcement/$', announcement.delete_announcement),
    
    
    
    url(r'^courses/discussion/$', discussion.discussion),
    url(r'^courses/add_discussion_page/$', discussion.add_discussion_page),
    url(r'^courses/add_discussion/$', discussion.add_discussion),
    url(r'^courses/list_discussion/$', discussion.list_discussion),

)
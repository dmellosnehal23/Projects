"""
6, Apr 2013

Example bottle (python) RESTful web service.

This example provides a basic setup of a RESTful service

Notes
1. example should perform better content negotiation. A solution is
   to use minerender (https://github.com/martinblech/mimerender)
"""


import json

from json import JSONEncoder
from bson import ObjectId

# bottle framework
from bottle import request, response, route


from admin import Admin
from user import User
from course import Course
from category import Category
from quiz import Quiz
from announcement import Announcement
from discussion import Discussion
from message import Message

admin = None
user = None 
course = None 
category = None
quiz = None 
announcement = None
discussion = None
message = None

class MongoEncoder(JSONEncoder):
    def default(self, obj, **kwargs):
        if isinstance(obj, ObjectId):
            return str(obj)
        else:            
            return JSONEncoder.default(obj, **kwargs)

def setup(base,conf_fn):
    print '\n**** service initialization ****\n'
    
    global admin 
    admin = Admin()
    
    global user 
    user = User()
    
    global course
    course = Course() 
    
    global category
    category = Category()
    
    global quiz
    quiz = Quiz() 
    
    global announcement
    announcement = Announcement() 
    
    global discussion
    discussion = Discussion()
    
    global message
    message = Message()
    

### --------------------
###    User requests
### --------------------

# ***************************************USERS*********************************************

#Author:SNEHAL- post createUser 
@route('/user', method='POST') 
def createUser():
    try:
        data = request.body.read()
        #data = request.POST.get()
        print data
        
        if not data:
            return errorResponse(400, 'No data received')
        
        post_data = json.loads(data)
        if not post_data.has_key('email'):
            return errorResponse(400, 'No email provided')
    
        if not '@' in post_data['email']:
            return errorResponse(400, 'Invalid email %s' % post_data['email'])
        
        id = user.create(post_data)
        if not id:
            return errorResponse(500, 'Failed to create user')
    
        response.status = 201
        return encodeResponse({"_id" : id})
    except (RuntimeError, ValueError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')
    
#Author:SNEHAL- get getUser
@route('/user/:email', method='GET')
def getUserByEmail(email):
    try: 
        if not '@' in email: 
            return errorResponse(400, 'Invalid email id %s' % email)
    
        else: 
            data = user.getByEmail(email)
            #do additonal checks
            if not data:
                return errorResponse(404, 'No user with email %s' % email)
        
            response.status = 200
            return encodeResponse(data)
    except (RuntimeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')

#Author: SNEHAL    
@route('/user/update/:email', method = 'PUT')     
def updateUser1(email):
    return updateUser(email)
        
#Author:SNEHAL - put updateUser
@route('/user/:email', method = 'PUT')     
def updateUser(email):
    try:
        if not '@' in email:
            return errorResponse(400, 'Invalid email %s' % email)
        
        data = request.body.read()
        print data
        
        if not data:
            return errorResponse(400, 'No data received')
        
        post_data = json.loads(data)
        
        status = user.update(email, post_data)
        if not status:
            return errorResponse(500, 'Failed to update user')
    
        response.status = 200
    except (RuntimeError, ValueError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')
    
#Author:SNEHAL-delete deleteUser
@route('/user/:email', method = 'DELETE')
def deleteUser(email):
    try:
        if not '@' in email:
            return errorResponse(400, 'Invalid email %s' % email)
        
        status = user.delete(email)
        #if not status:
        #    return errorResponse(404, 'Unable to delete user %s' % email)
        
    except (RuntimeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')


# ***************************************CATEGORY*********************************************
#Author: SNEHAL - post addCategory
@route('/category', method='POST') 
def createCategory():
    try:
        data = request.body.read()
        print data
        if not data:
            return errorResponse(400, 'No data received')
        post_data = json.loads(data)
        if not post_data.has_key('name'):
            return errorResponse(400, 'No categoryname provided')

        id = category.create(post_data)
        if not id:
            return errorResponse(500, 'Failed to create category')
    
        response.status = 201
        return encodeResponse({"_id" : id})
    except (RuntimeError, ValueError, AttributeError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  
    
# Author: SNEHAL- get getCategory
@route('/category/:id', method='GET')
def getCategoryById(id):
    try: 
        data = category.getById(id)
        #do additonal checks
        if not data:
            return errorResponse(404, 'No category with Id %s' % id)
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  

@route('/category/list', method='GET') 
def listCategory1():
    return listCategory()

 #Author: SNEHAL- get listCourses  
@route('/category', method='GET') 
def listCategory():
    try: 
        data =  category.list()
        #do additonal checks
        if not data:
            return errorResponse(404, 'No category available')
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 

#Author: SNEHAL-delete deleteCategory
@route('/category/:id', method = 'DELETE')
def deleteCategory(id):
    try:
        status = category.delete(id)
        #if not status:
        #    return errorResponse(404, 'Unable to delete category %s' % id)
               
    except (KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 

@route('/category/update/:id', method = 'PUT')     
def updateCategory1(id):
    return  updateCourse(id)

#SNEHAL - put updateUser
@route('/category/:id', method = 'PUT')     
def updateCategory(id):
    try:
        data = request.body.read()
        print data
        
        if not data:
            return errorResponse(400, 'No data received')
        
        post_data = json.loads(data)
        
        status = category.update(id, post_data)
        if not status:
            return errorResponse(500, 'Failed to update category')
    
        response.status = 200
    except (RuntimeError, ValueError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')
 
    
# ***************************************COURSES*********************************************

#Author: SNEHAL - post addCourse
@route('/course', method='POST') 
def createCourse():
    try:
        data = request.body.read()
        print data
        
        if not data:
            return errorResponse(400, 'No data received')
        
        post_data = json.loads(data)
        
        if not post_data.has_key('category') or not post_data.has_key('title') or not post_data.has_key('section') or not post_data.has_key('term') or not post_data.has_key('year') :
            return errorResponse(400, 'No category/title/section provided')
    
        id = course.create(post_data)
        if not id:
            return errorResponse(500, 'Failed to create course')
    
        response.status = 201
        
        user.updateOwnedCourse(post_data['email'], id)
        
        return encodeResponse({"_id" : str(id)})
    except (RuntimeError, ValueError, AttributeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')   
   
  
# Author: SNEHAL- get getCourse
@route('/course/:id', method='GET')
def getCourseById(id):
    try: 
        data = course.getById(id)
        print data
        #do additonal checks
        if not data:
            return errorResponse(404, 'No course with Id %s' % id)
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  

#Author: SNEHAL-delete deleteCourse
@route('/course/:id', method = 'DELETE')
def deleteCourse(id):
    try:
        status = course.delete(id)
        #if not status:
        #    return errorResponse(404, 'Unable to delete course %s' % id)       
    except (RuntimeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 

#Author: SNEHAL
@route('/course/list', method='GET') 
def listCourse1():
     return listCourse()
 
#Author: SNEHAL- get listCourses  
@route('/course', method='GET') 
def listCourse():
    try: 
        data =  course.list()
        #do additonal checks
        if not data:
            return errorResponse(404, 'No courses available')
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  

#Author: SNEHAL
@route('/course/update/:id', method = 'PUT')     
def updateCourse1(id):
    return  updateCourse(id)

#Author: SNEHAL - put updateUser
@route('/course/:id', method = 'PUT')     
def updateCourse(id):
    try:
        data = request.body.read()
        print data
        
        if not data:
            return errorResponse(400, 'No data received')
        
        post_data = json.loads(data)
        
        status = course.update(id, post_data)
        if not status:
            return errorResponse(500, 'Failed to update course')
    
        response.status = 200
    except (RuntimeError, ValueError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')

            
# ***************************************QUIZES*********************************************

#Author: SNEHAL
@route('/quizzes', method='POST')
def createQuiz1():        
    return createQuiz()

#Author: SNEHAL - post addQuiz
@route('/quiz', method='POST') 
def createQuiz():
    try:
        data = request.body.read()
        print data
        if not data:
            return errorResponse(400, 'No data received')
        post_data = json.loads(data)
        if not post_data.has_key('questions'):
            return errorResponse(400, 'No questions provided')
    
        id = quiz.create(post_data)
        if not id:
            return errorResponse(500, 'Failed to create quiz')
    
        response.status = 201
        return encodeResponse({"_id" : id})
    except (RuntimeError, ValueError, AttributeError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')   

  
# Author: SNEHAL- get getQuiz
@route('/quiz/:id', method='GET')
def getQuizById(id):
    try: 
        data = quiz.getById(id)
        #do additonal checks
        if not data:
            return errorResponse(404, 'No quiz with Id %s' % id)
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  

#Author: SNEHAL-delete deleteQuiz
@route('/quiz/:id', method = 'DELETE')
def deleteQuiz(id):
    try:
        status = quiz.delete(id)
        #if not status:
        #    return errorResponse(404, 'Unable to delete quiz %s' % id)       
    except (RuntimeError, TypeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 
 
#Author: SNEHAL
@route('/quiz/list', method='GET') 
def listQuizzes1():
    return listQuizzes()

#Author: SNEHAL
@route('/quiz', method='GET') 
def listQuizzes():
    return listCourseQuizzes(None)

#Author: SNEHAL
@route('/quiz/update/:id', method = 'PUT')   
def updateQuiz1(id):
    return updateQuiz(id)
    
#SNEHAL - put updateQuiz
@route('/quiz/:id', method = 'PUT')       
def updateQuiz(id):
    try:
        data = request.body.read()
        print data
        
        if not data:
            return errorResponse(400, 'No data received')
      
        post_data = json.loads(data)
        
        status = quiz.update(id, post_data)
        if not status:
            return errorResponse(500, 'Failed to update quiz')
     
        response.status = 200
    except (RuntimeError, ValueError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 
     
 
 # ***************************************ANNOUNCEMENT*********************************************
#Author: SNEHAL
@route('/announcements', method='POST') 
def createAnnouncement1():
    
    return createAnnouncement()
#Author: SNEHAL - post addCategory
@route('/announcement', method='POST') 
def createAnnouncement():
    try:
        data = request.body.read()
        print data
        if not data:
            return errorResponse(400, 'No data received')
        post_data = json.loads(data)
        if not post_data.has_key('title') or not post_data.has_key('description'):
            return errorResponse(400, 'No title/description provided')

        id = announcement.create(post_data)
        if not id:
            return errorResponse(500, 'Failed to create announcement')
    
        response.status = 201
        return encodeResponse({"_id" : id})
    except (RuntimeError, ValueError, AttributeError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  
  

# Author: SNEHAL- get getCategory
@route('/announcement/:id', method='GET')
def getAnnouncementById(id):
    try: 
        data = announcement.getById(id)
        #do additonal checks
        if not data:
            return errorResponse(404, 'No announcement with Id %s' % id)
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  

#Author: SNEHAL
@route('/announcement/list', method='GET') 
def listAnnouncement1():
    return listAnnouncement()

 #Author: SNEHAL- get listCourses  
@route('/announcement', method='GET') 
def listAnnouncement():
    try: 
        data =  announcement.list()
        #do additonal checks
        if not data:
            return errorResponse(404, 'No announcement available')
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 

#Author: SNEHAL-delete deleteCategory
@route('/announcement/:id', method = 'DELETE')
def deleteAnnouncement(id):
    try:
        status = announcement.delete(id)
        #if not status:
        #    return errorResponse(404, 'Unable to delete announcement %s' % id)       
    except (KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 

#Author: SNEHAL
@route('/announcement/update:id', method = 'PUT')       
def updateAnnouncement1(id):
    return updateDiscussion(id)
 
#Author: SNEHAL   
@route('/announcement/:id', method = 'PUT')       
def updateAnnouncement(id):
    try:
        data = request.body.read()
        print data
        
        if not data:
            return errorResponse(400, 'No data received')
      
        post_data = json.loads(data)
        
        status = announcement.update(id, post_data)
        if not status:
            return errorResponse(500, 'Failed to update announcement')
     
        response.status = 200
    except (RuntimeError, ValueError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 
     
       
# ***************************************DISCUSSION*********************************************
#Author: SNEHAL - post addCategory
@route('/discussions', method='POST') 
def createDiscussion():
    try:
        data = request.body.read()
        print data
        if not data:
            return errorResponse(400, 'No data received')
        post_data = json.loads(data)
        if not post_data.has_key('title') or not post_data.has_key('created_by'):
            return errorResponse(400, 'No title/email provided')

        id = discussion.create(post_data)
        #if id is not None:
        #    return errorResponse(500, 'Failed to create discussion')
    
        response.status = 201
        return encodeResponse({"id" : id})
    except (RuntimeError, ValueError, AttributeError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  

#Author: SNEHAL-delete deleteDiscussion
@route('/discussion/:id', method = 'DELETE')
def deleteDiscussion(id):
    try:
        status = discussion.delete(id)
        #if not status:
        #    return errorResponse(404, 'Unable to delete discussion %s' % id)       
    except (KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 
    
#Author: SNEHAL
@route('/discussion/list', method = 'GET')
def listDiscussion1(): 
    return listDiscussion()

#Author: SNEHAL
@route('/discussion', method = 'GET')
def listDiscussion():    
    try: 
        data =  discussion.listByUserOrCourse(None, None)
        #do additonal checks
        if not data:
            return errorResponse(404, 'No discussion available')
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 

#Author: SNEHAL
@route('/discussion/:id', method='GET')
def getDiscussionById(id):
    try: 
        data = discussion.getById(id)
        #do additonal checks
        if not data:
            return errorResponse(404, 'No discussion with Id %s' % id)
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  

#Author: SNEHAL updateDiscussion
@route('/discussion/update:id', method = 'PUT')       
def updateDiscussion1(id):
    return updateDiscussion(id)
 
#Author: SNEHAL   updateDiscussion 
@route('/discussion/:id', method = 'PUT')       
def updateDiscussion(id):
    try:
        data = request.body.read()
        print data
        
        if not data:
            return errorResponse(400, 'No data received')
      
        post_data = json.loads(data)
        
        status = discussion.update(id, post_data)
        if not status:
            return errorResponse(500, 'Failed to update discussion')
     
        response.status = 200
    except (RuntimeError, ValueError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 
     
# ***************************************COMBINATIONS*********************************************
#Author:SNEHAL- post enrollCourse
@route('/course/enroll', method='PUT')
def enrollCourse1():
    data = request.body.read()
    print data
        
    if not data:
        return errorResponse(400, 'No data received from data')
        
    print "before loading json data"
    post_data = json.loads(data)
    print "after loading json data"
    print post_data
    #if not post_data.has_key('email') or not post_data.has_key('courseid'):
     #   return errorResponse(400, 'No email/courseId provided')
    
    return enrollCourse(post_data['email'], post_data['courseId'])

#Author:SNEHAL- post enrollCourse
@route('/user/:email/course/:id', method='PUT')
def enrollCourse(email, id):
    try:
        if not '@' in email: 
            return errorResponse(400, 'Invalid email id %s' % email)
        
        status = user.enroll(email, id)
        if not status:
            return errorResponse(500, 'Internal Server Error')
        
        response.status = 200
    except (RuntimeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')   
    
#Author:SNEHAL- post dropCourse
@route('/course/drop', method='DELETE')
def  dropCourse1():
    data = request.body.read()
    print data
     
    if not data:
        return errorResponse(400, 'No data received')
    
    post_data = json.loads(data)
    
    if not post_data.has_key('email') or not post_data.has_key('courseId'):
        return errorResponse(400, 'No email/courseId provided')
    return dropCourse(post_data['email'], post_data['courseId'])
 
#Author:SNEHAL- post dropCourse
@route('/user/:email/course/:id', method='DELETE')
def dropCourse(email, id):
    try:
        if not '@' in email: 
            return errorResponse(400, 'Invalid email id %s' % email)
        
        user.drop(email, id)
        
        response.status = 200
    except (RuntimeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  
       
#Author: SNEHAL
@route('/user/:email/discussion', method='GET') 
def listUserDiscussion(email):
    try: 
        if not '@' in email:
            return errorResponse(400, 'Invalid email %s' % email)
        
        data =  discussion.listByUserOrCourse(email, None)
        #do additonal checks
        if not data:
            return errorResponse(404, 'No discussion available')
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  
      
#Author: SNEHAL       
@route('/course/:id/announcement', method='GET') 
def listCourseAnnouncement(id): 
    try:
        data =  announcement.listByCourse(id)
        return encodeResponse(data)   
    except (RuntimeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')

#Author: SNEHAL  
@route('/course/:id/discussion', method='GET') 
def listCourseDiscussion(id):
    try: 
        data =  discussion.listByUserOrCourse(None, id)
        #do additonal checks
        if not data:
            return errorResponse(404, 'No discussion available')
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')   
  
#Author: SNEHAL- get listCourses  
@route('/course/:id/quiz', method='GET') 
def listCourseQuizzes(id):
    try: 
        data =  quiz.list(id)
        #do additonal checks
        if not data:
            return errorResponse(404, 'No courses available')
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  

# ***************************************MESSAGES*********************************************

#Author: SNEHAL - post adddiscussion
@route('/discussion/:id/messages', method='POST') #WORKS
def createDiscussionMessage1(id):
    return createDiscussionMessage(id)
    
#Author: SNEHAL - post adddiscussion
@route('/discussion/:id/message', method='POST') #WORKS
def createDiscussionMessage(id):
    try:
        data = request.body.read()
        print data
        if not data:
            return errorResponse(400, 'No data received')
        post_data = json.loads(data)
        if not post_data.has_key('email') or not post_data.has_key('content'):
            return errorResponse(400, 'No email/content provided')

        mid = message.create(id, post_data)
        if not mid:
            return errorResponse(500, 'Failed to create message')
    
        response.status = 201
        return encodeResponse({"_id" : mid})
    except (RuntimeError, ValueError, AttributeError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')  

#Author: SNEHAL-delete deleteDiscussionMessage
@route('/message/:mid/discussion/:id', method = 'DELETE') #WORKS
def deleteDiscussionMessage1(mid, id):
    return deleteDiscussionMessage(mid)

#Author: SNEHAL-delete deleteDiscussionMessage
@route('/message/:id', method = 'DELETE')
def deleteDiscussionMessage(id):
    try:
        status = message.delete(id)
        #if not status:
        #    return errorResponse(404, 'Unable to delete discussion %s' % id)       
    except (KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 
 
#Author: SNEHAL-GET listDiscussionMessage   
@route('/discussion/:id/messages', method = 'GET')
def listDiscussionMessages1(id): #WORKS
    return listDiscussionMessages(id)

#Author: SNEHAL-GET listDiscussionMessage 
@route('/discussion/:id/message', method = 'GET')
def listDiscussionMessages(id):    #WORKS
    try: 
        data =  message.listByDiscussion(id)
        #do additonal checks
        print data
        if not data:
            return errorResponse(404, 'No discussion message available')
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')

#Author: SNEHAL-GET listDiscussionMessageById     
@route('/discussion/:id/message/:mid', method='GET')
def getDiscussionMessageById1(id, mid): #WORKS
    return getDiscussionMessageById(mid)

#Author: SNEHAL-GET listDiscussionMessageById  
@route('/message/:id', method='GET')
def getDiscussionMessageById(id):  #WORKS
    try: 
        data = message.getById(id)
        #do additonal checks
        if not data:
            return errorResponse(404, 'No message with Id %s' % id)
        
        response.status = 200
        return encodeResponse(data)
    except (RuntimeError, TypeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 
     
#Author: SNEHAL-GET UpdateDiscussionMessageById  
@route('/message/update/:mid/discussion/:id', method = 'PUT')       
def updateDiscussionMessage1(id, mid):
    return updateDiscussion(id)

#Author: SNEHAL    
@route('/message/:id', method = 'PUT')       
def updateDiscussionMessage(id):
    try:
        data = request.body.read()
        print data
        
        if not data:
            return errorResponse(400, 'No data received')
      
        post_data = json.loads(data)
        
        status = message.update(id, post_data)
        if not status:
            return errorResponse(500, 'Failed to update discussion')
     
        response.status = 200
    except (RuntimeError, ValueError, TypeError, KeyError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error') 
              
# ***************************************MISC*********************************************
#Author: SNEHAL
@route('/admin/resetdb', method='GET')
def resetDB():
    try:
        admin.resetDB()
        response.status = 200
    except (RuntimeError) as err:
        print str(err)
        return errorResponse(500, 'Internal Server Error')
 
#Author: SNEHAL      
def encodeResponse(data):
    return json.dumps(data, cls=MongoEncoder)   

def errorResponse(code, message):
    response.status = code
    err = {}
    err['error'] = message
    return encodeResponse(err)

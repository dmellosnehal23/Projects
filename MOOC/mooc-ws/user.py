'''
Created on May 1, 2013

@author: SNEHAL D'MELLO
'''

#import from mongodb
from pymongo import Connection
from django.core.exceptions import ValidationError
from course import Course

class User(object):
    
    db = None
    
    '''
    classdocs
    '''
    #constructor
    def __init__(self):
        self.connection = Connection('localhost', 27017)
        self.db = self.connection.MOOC
        self.db_users = self.db['usercollection']
        
        self.course = Course()
     
    def reset(self):
        self.db_users.remove()
        
    # get user by email          
    def getByEmail(self, email):
        info = self.db_users.find_one({'email' : email})
        return info
    
    #create user 
    def create(self, info):
        try:
            data = {}
            data['email'] = info['email'] 
            data['own'] = []
            data['enrolled'] = []
            data['quizzes'] = [] 
             
            return self.db_users.save(data)
        except ValidationError as ve:
            print str(ve)
            return False
        
    
    #update user
    def update(self, email, info):
        try:
            if info.has_key('email'):
                del info['email']
                
            data={}                
            data = info #TODO
            self.db_users.update({'email' : email}, {"$set" : data})
        except ValidationError as ve:
            print str(ve)
            return False
        
        return True
    
    def updateOwnedCourse(self, email, courseId):
        self.db_users.update({'email' : email}, {"$addToSet" :{'own' : str(courseId)}})
    
    #delete user
    def delete(self, email):
        return self.db_users.remove({'email' : email})
        
    #enroll user
    def enroll(self, email, courseId):
        #check if course is available
        course = self.course.getById(courseId)
        if not course:
            return None   #course not available 

        
        #add new course
        self.db_users.update({'email' : email}, {"$addToSet" :{'enrolled' : courseId}})
        
        return True 
    
    #enroll user
    def drop(self, email, courseId):
        #add new course
        self.db_users.update({'email' : email}, {"$pull" :{'enrolled' : courseId}})
        
        return True     
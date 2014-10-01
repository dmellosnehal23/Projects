'''
Created on May 1, 2013

@author: SNEHAL D'MELLO
'''

#import from mongodb
from pymongo import Connection
from bson import ObjectId
from django.core.exceptions import ValidationError
from announcement import Announcement

class Course(object):
    
    db = None
    
    '''
    classdocs
    '''
    #constructor
    def __init__(self):
        self.connection = Connection('localhost', 27017)
        self.db = self.connection.MOOC
        self.db_courses = self.db['coursecollection']
        self.db_announcement = Announcement()
     
    def reset(self):
        self.db_courses.remove()
                  
    # get course by Id          
    def getById(self, courseId):
        info = self.db_courses.find_one({'_id' : ObjectId(courseId)})#, {'_id' : True})
        return info
    
 
    #add course 
    def create(self, info):
        try:
            data = {}
            data['category'] = info['category']
            data['title'] = info['title']
            data['section'] = info['section']
            data['term'] = info['term'] 
            data['year'] = info['year'] 
            
            if info.has_key('dept') :
                data['dept'] = info['dept']
            else :
                data['dept'] = ''
                
            if info.has_key('Description') :
                data['Description'] = info['Description']
            else :
                data['Description'] = '' 
                 
            if info.has_key('attachment') :
                data['attachment'] = info['attachment']
            else :
                data['attachment'] = ''
            
            if info.has_key('version') :    
                data['version'] = info['version']
            else :
                data['version'] = ''
                            
            data['instructor'] = []
            if info.has_key('instructor') and not info['instructor']:
                instructor = info['instructor']
                data['instructor'].append({'name' : instructor['name'], 'email' : instructor['email']})
  
            if info.has_key('days') and not info['days']:
                data['days'] = info['days']
            else :
                data['days'] = []
                
            if info.has_key('hours') and not info['hours']:
                data['hours'] = info['hours']
            else :
                data['hours'] = []
                        
            return self.db_courses.save(data)
        except ValidationError as ve:
            print str(ve)
            return False
    
    #delete course
    def delete(self, courseId):
        return self.db_courses.remove({'_id' : ObjectId(courseId)}) 
    
        #self.db_courses.remove({})
        
    def list(self):
        data = self.db_courses.find({})# , {'_id' : True})   
        list = []
        for entry in data:
            courseId = entry['_id']
            courseIdStr = str(courseId)
            id = {'id' : courseIdStr}
            del entry['_id']
            entry.update(id)           
            list.append(entry)  
        print list
        return list
    
    #update course
    def update(self, courseId, info):
        try:
            #data={}
        
            #data['courseName'] = info['courseName']
            
            #self.db_courses.update({'courseId' : courseId}, {"$set" : data})
            self.db_courses.update({'_id' : ObjectId(courseId)}, {"$set" : info})
        except ValidationError as ve:
            print str(ve)
            return False      
        return True

        
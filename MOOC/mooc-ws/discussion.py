'''
Created on May 1, 2013

@author: SNEHAL D'MELLO
'''
import datetime

#import from mongodb
from pymongo import Connection
from bson import ObjectId
from django.core.exceptions import ValidationError
#from datetime import datetime


class Discussion(object):
    
    db = None
    
    '''
    classdocs
    '''
    #constructor
    def __init__(self):
        self.connection = Connection('localhost', 27017)
        self.db = self.connection.MOOC
        self.db_discussion = self.db['discussioncollection']
        #db_users = db['users']

    def reset(self):
        self.db_discussion.remove()
                      
    # get discussion by Id          
    def getById(self, discussionId):
        info = self.db_discussion.find_one({'_id' : ObjectId(discussionId)})
        return info
    
     
    def create(self, info):        
        try:
            data = {}
            data['title'] = info['title']
            #data['description'] = info['description']
            now = datetime.datetime.now()
            currentDate = str(now) 
            data['created_at'] = currentDate
            data['updated_at'] = currentDate
            data['created_by'] = info['created_by']
            #if info['courseId'] is not None:
            #    data['courseId'] = info['courseId']
            return self.db_discussion.save(data)
        except ValidationError as ve:
            print str(ve)
            return False

    
    #delete discussion
    def delete(self, discussionId):
        return self.db_discussion.remove({'_id' : ObjectId(discussionId)}) 
        #self.db_discussion.remove({})

     
    
    def listByUserOrCourse(self, email, courseId):
        print courseId
        cond = {}
        if email:
            cond['created_by'] = email
        if courseId:
            cond['courseId'] = courseId
            
        print cond    
        data = self.db_discussion.find(cond)   
        list = []
        for entry in data:
            discussionId = entry['_id']
            discussionIdStr = str(discussionId)
            id = {'id' : discussionIdStr}
            del entry['_id']
            entry.update(id)   
            list.append(entry)
        print list
        return list
    
    #update discussion
    def update(self, discussionId, info):
        try:
            data={}
            data = info #TDOD validations
            
            self.db_discussion.update({'_id' : ObjectId(discussionId)}, {"$set" : data})
        except ValidationError as ve:
            print str(ve)
            return False
        
        return True
        
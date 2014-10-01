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


class Announcement(object):
    
    db = None
    
    '''
    classdocs
    '''
    #constructor
    def __init__(self):
        self.connection = Connection('localhost', 27017)
        self.db = self.connection.MOOC
        self.db_announcement = self.db['announcementcollection']
        #db_users = db['users']

    def reset(self):
        self.db_announcement.remove()
                      
    # get announcement by Id          
    def getById(self, announcementId):
        info = self.db_announcement.find_one({'_id' : ObjectId(announcementId)})
        return info
    
        
    #add announcement 
    def create(self, info):        
        try:
            data = {}
            data['title'] = info['title']
            data['description'] = info['description']
            now = datetime.datetime.now()
            currentDate = str(now) 
            data['postDate'] = currentDate
            data['status'] = 0
            data['courseId'] = info['courseId']
            
            return self.db_announcement.save(data)
        except ValidationError as ve:
            print str(ve)
            return False

    
    #delete announcement
    def delete(self, announcementId):
        return self.db_announcement.remove({'_id' : ObjectId(announcementId)}) 
        #self.db_announcement.remove({})

     
    # list all announcements   
    def list(self):
        return self.listByCourse(None)
    # list announcements by course
    def listByCourse(self, courseId):
        print courseId
        cond = {}
        if courseId:
            cond['courseId'] = courseId
            
        data = self.db_announcement.find()   
        list = []
        for entry in data:
            announcementId = entry['_id']
            announcementIdStr = str(announcementId)
            id = {'id' : announcementIdStr}
            del entry['_id']
            entry.update(id)
            print entry
            list.append(entry)      
        print list
        return list
    
    #update announcement
    def update(self, announcementId, info):
        try:
            data={}
            data = info # TDOD validations
            
            self.db_announcement.update({'_id' : ObjectId(announcementId)}, {"$set" : data})
        except ValidationError as ve:
            print str(ve)
            return False
        
        return True
        
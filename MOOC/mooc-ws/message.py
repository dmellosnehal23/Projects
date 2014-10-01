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


class Message(object):
    
    db = None
    
    '''
    classdocs
    '''
    #constructor
    def __init__(self):
        self.connection = Connection('localhost', 27017)
        self.db = self.connection.MOOC
        self.db_message = self.db['messagecollection']
        #db_users = db['users']

    def reset(self):
        self.db_message.remove()
                      
    # get message by Id          
    def getById(self, messageId):
        info = self.db_message.find_one({'_id' : ObjectId(messageId)})
        return info
    
     
    def create(self, discussionId, info):        
        try:
            data = {}
            data['title'] = info['title']
            data['content'] = info['content']
            data['discussionId'] = discussionId
            data['created_by'] = info['email']
            now = datetime.datetime.now()
            currentDate = str(now)            
            data['created_at'] = currentDate
            data['updated_at'] = currentDate
            
            return self.db_message.save(data)
        except ValidationError as ve:
            print str(ve)
            return False
    
    #delete message
    def delete(self, messageId):
        return self.db_message.remove({'_id' : ObjectId(messageId)}) 
        #self.db_message.remove({})  
    
    def listByDiscussion(self, discussionId):
        print discussionId
  
        data = self.db_message.find({'discussionId' : discussionId})   
        list = []
        for entry in data:
            list.append(entry)      
        
        return list
    
    #update message
    def update(self, messageId, info):
        try:
            data={}
            data = info #TDOD validations
            
            self.db_message.update({'_id' : ObjectId(messageId)}, {"$set" : data})
        except ValidationError as ve:
            print str(ve)
            return False
        
        return True
        
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


class Category(object):
    
    db = None
    
    '''
    classdocs
    '''
    #constructor
    def __init__(self):
        self.connection = Connection('localhost', 27017)
        self.db = self.connection.MOOC
        self.db_category = self.db['categorycollection']
        #db_users = db['users']

    def reset(self):
        self.db_category.remove()
                      
    # get category by Id          
    def getById(self, categoryId):
        info = self.db_category.find_one({'_id' : ObjectId(categoryId)})#, {'_id' : True})
        #info['_id'] = str(info['_id'])
        return info
    
    #add category 
    def create(self, info):
        try:
            data = {}
            data['name'] = info['name']
            data['description'] = info['description']
            now = datetime.datetime.now()
            currentDate = str(now) 
            data['createDate'] = currentDate
            data['status'] = 0
            
            return self.db_category.save(data)
        except ValidationError as ve:
            print str(ve)
            return False

    
    #delete category
    def delete(self, categoryId):
        return self.db_category.remove({'_id' : ObjectId(categoryId)}) 
        #self.db_category.remove({})
     
    # list all categories   
    def list(self):
        data = self.db_category.find({}) #, {'_id' : True})   
        list = []
        for entry in data:
            categoryId = entry['_id']
            categoryIdStr = str(categoryId)
            id = {'id' : categoryIdStr}
            del entry['_id']
            entry.update(id)
            list.append(entry)      
        
        return list
    
    #update category
    def update(self, categoryId, info):
        try:
            data={}
            data = info #TODO
            
            self.db_category.update({'_id' : ObjectId(categoryId)}, {"$set" : data})
        except ValidationError as ve:
            print str(ve)
            return False
        
        return True
        
'''
Created on May 1, 2013

@author: SNEHAL D'MELLO
'''

#import from mongodb
from pymongo import Connection
from django.core.exceptions import ValidationError
from bson import ObjectId

class Quiz(object):
    
    db = None
    
    '''
    classdocs
    '''
    #constructor
    def __init__(self):
        self.connection = Connection('localhost', 27017)
        self.db = self.connection.MOOC
        self.db_quizzes = self.db['quizcollection']

    def reset(self):
        self.db_quizzes.remove()
                      
    # get quiz by Id          
    def getById(self, quizId):
        info = self.db_quizzes.find_one({'_id' : ObjectId(quizId)})
        return info
    
 
    #add quiz 
    def create(self, info):
        try:
            #data = {}
            data = info #TODO validations

            print data
            return self.db_quizzes.save(data)
        except ValidationError as ve:
            print str(ve)
            return False

    
    #delete quiz
    def delete(self, quizId):
        return self.db_quizzes.remove({'_id' : ObjectId(quizId)})
        
    def list(self, courseId):
        cond = {}
        if courseId:
            cond['courseId'] = courseId
            
        data = self.db_quizzes.find(cond)   
        list = []
        for entry in data:
            list.append(entry)  
        return list
    
    #update quiz
    def update(self, quizId, info):
        try:
            data={}
            data = info # check
            
            self.db_quizzes.update({'_id' : ObjectId(quizId)}, {"$set" : data})
        except ValidationError as ve:
            print str(ve)
            return False
        
        return True
        
'''
Created on May 12, 2013
@author: rbelavigi
'''
# from moo import getCourseById
import unittest
import json
from pymongo import Connection
from moo.course import Course
from moo.user import User
from moo.category import Category
from moo.quiz import Quiz
from moo.announcement import Announcement
from moo.discussion import Discussion
from moo.moo import Moo
import datetime
from chardet.test import count

cr = Course()
ur = User()
ct = Category()
q = Quiz()
announce = Announcement()
discuss = Discussion()
mo = Moo()

class TestingMoo(unittest.TestCase):

#--------------test cases of User-------------------------------        
    def testgetUser(self):
        self.email = "rachana@gmail.com"
        result = ur.getByEmail(self.email)
        self.assertIsNotNone(result) 
        
    def testUpdateUser(self):
        self.email = "siri1@gmail.com"
        self.info = {}
        self.info['email'] = 'siri@gmail.com'
        self.info['password'] = 'test'
        self.info['fname'] = 'siri'
        result = ur.update(self.email, self.info)
        self.assertTrue(result)
        
    def testdeleteUser(self):
        self.email = 'test@gmail.com'
        result = ur.delete(self.email)
        self.assertIsNone(result)
        
    def testcreateUser(self):
        self.info ={}
        self.info['email'] = 'test@gmail.com'
        self.info['password'] = 'test'
        self.info['own'] = []
        self.info['enrolled'] = []
        self.info['quizzes'] = []
        if not self.info.has_key('emailId') or not self.info.has_key('password'):
            self.assertRaises('You need to enter both email n password')
        else:   
            result = ur.create(self.info)
            self.assertTrue(result)
            
#-----------------test cases for course category--------------------
        
    def testcreateCategory(self):
        self.info ={}
        self.info['name'] = 'software engg'
        self.info['description'] = 'software engg department' 
        now = datetime.datetime.now()
        currentDate = str(now) 
        self.info['createDate'] = currentDate
        self.info['status'] = 0
        result = ct.create(self.info)
        self.assertTrue(result)
        
    def testgetCategory(self):
        self.id = '519026cd1d41c80e95d6fc74'
        result = ct.getById(self.id)
        self.assertTrue(result)
        
    def testdeleteCategory(self):
        self.id = '519029bc1d41c80e95d6fc75'
        result = ct.delete(self.id)
        self.assertIsNone(result)
        
    def testlistcategory(self):
        result = ct.list()
        self.assertTrue(result)
        
    def testUpdatecategory(self):
        self.id = '51901f1a1d41c80eacf9457b'
        self.info = {}
        self.info['categoryName'] = 'network engg'
        result = ct.update(self.id, self.info)
        self.assertTrue(result)
        
#------------ test case of course----------------------------------        
    def testcreateCourse(self):
        self.info = {}
        self.info['category'] = '519026cd1d41c80e95d6fc74'
        self.info['title'] = 'Enterprise distributed components '
        self.info['section'] = '1'
        self.info['term'] =  'spring'
        self.info['year'] = '2013'
        result = cr.create(self.info)
        self.assertTrue(result) 
        
    def testgetCourse(self):
        self.id='519034b91d41c81d1748e519'
        result = cr.getById(self.id)
        self.assertIsNotNone(result)
        
    def testdeleteCourse(self):
        self.id = '519036481d41c80e95d6fc78'
        result = cr.delete(self.id)
        self.assertIsNone(result)
        
    def testlistCourse(self):
        result = cr.list()
        self.assertTrue(result)
        
    def testupdateCourse(self):
        self.id = '51902a721d41c80e95d6fc76'
        self.info = {}
        result = cr.update(self.id, self.info)
        self.assertTrue(result)
        
#------------test cases of quiz---------------------------------------

    def testcreateQuiz(self):
        self.courseid = '51902f231d41c80e95d6fc77'
        self.info = {}
        self.info['questions'] = [{"question":"Ques1",
        "options":["option1", "option2"], "answer":"option1","point": 1}, {"question": "Ques2",
        "options":["option1", "option2"], "answer": "option1", "point": 1}]
#         result = q.create(self.courseid, self.info)
        result = q.create(self.info)
        self.assertTrue(result)
        
    def testgetQuiz(self):
        self.courseid = '51902f231d41c80e95d6fc77'
        self.quizid = '519042251d41c80e95d6fc79'
       #result = q.getById(self.courseid, self.quizid)
        result = q.getById(self.quizid)
        self.assertIsNotNone(result)
        
#     def testlistQuiz(self):
#         self.courseid = '51902f231d41c80e95d6fc77'
#         result =q.list(self.courseid)
#         result = q.list(self.courseid)
#         self.assertTrue(result)
        
    def testdeleteQuiz(self):
        self.courseid = '51902f231d41c80e95d6fc77'
        self.quizid = '519045e61d41c829136d81de'
#         result = q.delete(self.courseid,self.quizid)
        result = q.delete(self.quizid)
        self.assertIsNone(result)
        
    def testUpdateQuiz(self):
        self.quizid = '519042251d41c80e95d6fc79'
        self.info = {}
        self.info['questions'] = [{"question":"Ques3",
        "options":["option1", "option2"], "answer":"option1","point": 1}]
#         result = q.update(self.courseid,self.quizid,self.info)
        result = q.update(self.quizid,self.info)
        self.assertTrue(result)
        
#------------test cases of announcement-------------------------------

    def testcreateAnnouncement(self):
        self.data= {}
        self.data['title'] = 'Announcement1'
        self.data['description'] = 'Announcement1 description'
        self.now = datetime.datetime.now()
        self.currentDate = str(self.now)
        self.data['postDate'] =self.currentDate
        self.data['status'] = 0
        self.data['courseId'] = '51902f231d41c80e95d6fc7'
        result = announce.create(self.data)
        self.assertTrue(result)
       
    def testgetAnnouncement(self):
        self.announcementId = '51905d731d41c80e95d6fc7f'
        result = announce.getById(self.announcementId)
        self.assertTrue(result)
        
    def testdeleteAnnouncement(self):
        self.announcementId = '519060c21d41c80e95d6fc81'
        result = announce.delete(self.announcementId)
        self.assertIsNone(result)
        
    def testlistAnnouncement(self):
        result = announce.list()
        self.assertIsNotNone(result)
        
    def testlistAnnouncementbyCourse(self):
        self.courseid = '51902f231d41c80e95d6fc77'
        result = announce.listByCourse(self.courseid)
        self.assertIsNotNone(result)
        
    def testUpdateAnnouncement(self):
        self.announcementId = '51905d731d41c80e95d6fc7f'
        self.data = {}
        self.data['title'] = 'abcd'
        self.data['description'] = 'description of xyz'
        result = announce.update(self.announcementId, self.data)
        self.assertTrue(result)


#------------test cases of discussion---------------------------------

    def testCreateDiscussion(self):
        self.data={}
        self.  data['title'] = 'Discussion1'
        self.data['description'] = 'Discussion1 description'
        self.now = datetime.datetime.now()
        self.currentDate = str(self.now) 
        self.data['created_at'] = self.currentDate
        self.data['updated_at'] = self.currentDate
        self.data['email'] = 'siri@gmail.com'
        self.data['courseId'] = '51902f231d41c80e95d6fc77'
        result = discuss.create(self.data)
#         print(result)
        self.assertTrue(result)
# 

    def testCreateDiscussion1(self):
        self.data={}
        self.  data['title'] = 'Discussion1'
        self.data['description'] = 'Discussion1 description'
        self.now = datetime.datetime.now()
        self.currentDate = str(self.now) 
        self.data['created_at'] = self.currentDate
        self.data['updated_at'] = self.currentDate
        self.data['email'] = 'siri@gmail.com'
        self.data['courseId'] = '51902a721d41c80e95d6fc76'
        result = discuss.create(self.data)
        self.assertTrue(result)
        
    def testgetDiscussion(self):
        self.id = '5191614c1d41c80fddb16f4f'
        result = discuss.getById(self.id)
        self.assertIsNotNone(result)
         
    def testlistDiscussion(self):
        self.courseid = '51902f231d41c80e95d6fc77'
        self.email = 'siri@gmail.com'
        result = discuss.listByUserOrCourse(self.email, self.courseid)
        self.assertIsNotNone(result)
        
    def testdeleteDiscussion(self):
        self.disId = '519166731d41c814377cdc34'
        result = discuss.delete(self.disId)
        self.assertIsNone(result)
        
    def testenrollCourse(self):
        self.courseid = '51902f231d41c80e95d6fc77'
        self.email = 'siri@gmail.com'
        result = ur.enroll(self.email, self.courseid)
        self.assertTrue(result)
        
    def testenrollCourse1(self):
        self.courseid = '51902f231d41c80e95d6fc77'
        self.email = 'rachana@gmail.com'
        result = ur.enroll(self.email, self.courseid)
        self.assertTrue(result)
        
        
    def testdropCourse(self):
        self.courseid = '51902f231d41c80e95d6fc77'
        self.email = 'rachana@gmail.com'
        result = ur.drop(self.email, self.courseid)
        self.assertTrue(result)
        
    
#---------------------------------------------------------------------------------------------------

    
'''
Created on May 1, 2013

@author: SNEHAL D'MELLO
'''

from user import User
from course import Course
from category import Category
from quiz import Quiz
from announcement import Announcement
from discussion import Discussion

class Admin(object):
    
    db = None
    
    '''
    classdocs
    '''
    #constructor
    def __init__(self):        
        self.user = User()
        self.course = Course()
        self.category = Category()
        self.quiz = Quiz()
        self.announcement = Announcement()
        self.discussion = Discussion()
              
    def resetDB(self):
        self.user.reset()
        self.course.reset()
        self.category.reset()
        self.quiz.reset()
        self.announcement.reset()
        self.discussion.reset()
        
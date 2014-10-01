import json
import bottle
from bottle import route, run, request, abort
from pymongo import Connection
 
 
@route('/documents', method='PUT')
def put_document():
    return None
     
@route('/documents/:id', method='GET')
def get_document(id):
	return json.dumps({'file_id': 1, 'filename': "meet" , 'links_to' : "meet_link"})
 
run(host='localhost', port=9000)

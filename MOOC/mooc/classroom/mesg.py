from django.shortcuts import render_to_response

def add_message_page(request):
    discussion_id=email=request.POST.get('discussion_id', '')
    discussion_title=email=request.POST.get('discussion_title', '')
    return render_to_response('message_add.html',{"discussion_id":discussion_id,"discussion_title":discussion_title})
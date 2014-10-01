from django.db import models

# Create your models here.
class Site(models.Model):
    display_name=models.CharField(max_length=200)
    domain_name=models.TextField()
    default_url=models.BooleanField()
    def __unicode__(self):
        return self.display_name
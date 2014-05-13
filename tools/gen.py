#!/usr/bin/python

import random
import string
import sys

for i in range(0,int(sys.argv[1])):
	print ''.join(random.choice(string.ascii_uppercase + string.digits) for _ in range(100))

print 'END'

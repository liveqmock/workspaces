# Jython source file
import sys
import time

def work():
    
   a = sys.argv[1]
   b = sys.argv[2]
   
   
    
   time.sleep(1)
   print a+'10%'
   sys.stdout.flush()
   
   time.sleep(1)
   print b[0]+'20%'
   sys.stdout.flush()
   
   time.sleep(1)
   print '30%'
   sys.stdout.flush()
   
   time.sleep(1)
   print '40%'
   sys.stdout.flush()
   
   time.sleep(1)
   print '50%'
   sys.stdout.flush()
   
if __name__ == '__main__':
	work()

	

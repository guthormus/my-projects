import random
print('your new password:')

string="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&-_"
newpassword=""
for i in range(12):
    newpassword+=random.choice(string)

print(newpassword)
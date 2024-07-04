import random
intpe = int(input("Enter the number of nicknames you want: "))
print("your new nickname:")
nicknames = ["Shadow ","Raven ","Maverick ","Blaze ","Phoenix ","Jaguar ","Viper ","Titan ","Siren ", "Orion ","Nova ","Electra ","Zeus ","Aurora ","Spartan ","Rogue ","Echo ","Hunter ","Luna ","Cypher "]
nickname=''
for i in range(intpe):
    nickname+=random.choice(nicknames)

print(nickname)
print('your new password:')
string="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&-_"
newpassword=""
for i in range(16):
    newpassword+=random.choice(string)

print(newpassword)

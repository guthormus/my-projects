import random
intpe = int(input("Enter the number of nicknames you want: "))
print("your new nickname:")
nicknames = ["Ace", "Buddy", "Champ", "Duke", "Sparky", "Jazzy", "Sunny", "Ninja","Bubbles", "Gizmo", "Teddy", "Cookie", "Maverick", "Stitch", "Shadow","Flash", "Skippy", "Rocky", "Peanut", "Scout", "Buzz", "Chip", "Doodle","Smokey", "Snickers", "Tiger", "Pumpkin", "Panda", "Whiskers", "Fuzzy","Roo", "Sprout", "Binky", "Cuddles", "Waffles", "Pippin", "Bambi","Chester", "Pogo", "Daisy", "Marshmallow", "Boomer", "Ziggy", "Tater","Fluffy", "Snickerdoodle", "Bubba", "Wiggles", "Munchkin", "Chili","Sassy", "Tornado", "Pudding", "Bumblebee", "Gummy", "Hiccup","Jellybean", "Tinker", "Coco", "Doodlebug", "Snoopy", "Sprinkles","Muffin", "Nibbles", "Frodo", "Cinnamon", "Twinkle", "Pip", "Biscuit","Snuffy", "Gizmo", "Cupcake", "Kiki", "Buttons", "Bingo", "Lucky","Squeaky", "Zippy", "Wookiee", "Pogo", "Bubbles", "Yoshi", "Tater Tot","Rascal", "Flapjack", "Winky", "Snowball", "Chickpea", "Boo", "Fizz", "Pipkin", "Kiki", "Noodles", "Doodles", "Boo Boo", "Snoop", "Froppy", "Luna", "Luffy", "Yoda", "Leia", "Gandalf", "Smeagol","Spock", "Elmo", "Cheetor", "Mewtwo", "Toad", "Deku", "Shrek", "Misty", "Zelda", "Lara", "Spyro", "Chocobo", "Pikmin", "Jigglypuff""Taz", "Pebbles", "Buzzy", "Giggles", "Dynamo", "Lemonade","Goku", "Kirby", "Sonic", "Link", "Pikachu", "Ash", "Nami", "Naruto"]
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

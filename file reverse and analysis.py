import turtle
from tkinter import filedialog, Tk, Button, Frame
import os
import time
import random

def analyze_file(filepath):
    linec = 0
    letterc = 0
    spacec = 0
    wordc = 0
    totchar = 0
    
    with open(filepath, "r") as file:
        for line in file:
            linec += 1
            words = line.strip().split()
            wordc += len(words)
            for char in line:
                if char.isalpha():
                    letterc += 1
                elif char.isspace():
                    spacec += 1
                totchar += 1
    
    return linec, wordc, letterc, spacec, totchar

def reverse_file(filepath):
    reversed_content = ""
    with open(filepath, "r") as file:
        for line in file:
            reversed_content = line[::-1] + "\n" + reversed_content
    reversed_filepath = os.path.splitext(filepath)[0] + "_reversed.txt"
    with open(reversed_filepath, "w") as file:
        file.write(reversed_content)
    return reversed_filepath

def interstellar_setup():
    turtle.bgcolor("black")
    turtle.title("Interstellar File Analyzer")
    turtle.speed(0)
    turtle.hideturtle()

def create_starry_background(star_count=50):
    turtle.penup()
    turtle.pencolor("white")
    turtle.goto(-400, 300)
    turtle.speed(0)

    for _ in range(star_count):
        x = random.randint(-390, 290)
        y = random.randint(-290, 290)
        turtle.goto(x, y)
        turtle.dot(random.randint(2, 6), random.choice(["white", "lightblue", "lightyellow"]))

def cosmic_text(text, x, y, font_size, text_color):
    turtle.penup()
    turtle.goto(x, y)
    turtle.pencolor(text_color)
    turtle.write(text, font=("Arial", font_size, "bold"), align="left")

def display_results(lines, words, letters, spaces, total_chars, message=""):
    turtle.clear()
    create_starry_background()
    cosmic_text("INTERSTELLAR FILE ANALYSIS", -230, 150, 24, "white")
    cosmic_text(f"Lines: {lines}", -230, 100, 20, "lightblue")
    cosmic_text(f"Words: {words}", -230, 70, 20, "lightyellow")
    cosmic_text(f"Letters: {letters}", -230, 40, 20, "lightgreen")
    cosmic_text(f"Spaces: {spaces}", -230, 10, 20, "lightpink")
    cosmic_text(f"Total Characters: {total_chars}", -230, -20, 20, "purple")
    if message:
        cosmic_text(message, -230, -60, 20, "yellow")

def meteor_shower():
    for _ in range(10):
        x = random.randint(-390, 390)
        y = random.randint(-290, 290)
        turtle.goto(x, y)
        turtle.pencolor("cyan")
        turtle.dot(2)
        time.sleep(0.1)
        turtle.undo()

def analyze_and_display():
    filepath = filedialog.askopenfilename(filetypes=[("Text files", "*.txt")])
    
    if filepath and os.path.exists(filepath):
        lines, words, letters, spaces, total_chars = analyze_file(filepath)
        display_results(lines, words, letters, spaces, total_chars)

def reverse_and_display():
    filepath = filedialog.askopenfilename(filetypes=[("Text files", "*.txt")])
    
    if filepath and os.path.exists(filepath):
        reversed_filepath = reverse_file(filepath)
        display_results(0, 0, 0, 0, 0, f"Reversed file saved as: {os.path.basename(reversed_filepath)}")

def main():
    interstellar_setup()
    
    # Set up the tkinter root window
    root = Tk()
    root.title("Interstellar File Analyzer")

    # Create a frame for the buttons
    button_frame = Frame(root)
    button_frame.pack(side="bottom", padx=10, pady=10)

    # Create buttons for file analysis and reversal
    analyze_button = Button(button_frame, text="Analyze File", command=analyze_and_display)
    analyze_button.pack(side="left", padx=10)

    reverse_button = Button(button_frame, text="Reverse File", command=reverse_and_display)
    reverse_button.pack(side="left", padx=10)

    quit_button = Button(button_frame, text="Quit", command=root.quit)
    quit_button.pack(side="left", padx=10)

    # Display the initial cosmic text
    cosmic_text("INTERSTELLAR FILE ANALYSIS", -230, 150, 24, "white")

    turtle.mainloop()

if __name__ == "__main__":
    main()

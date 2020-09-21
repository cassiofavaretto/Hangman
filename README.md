# Hangman
Hangman game using Java and Springboot.

To start a new game send a request to route POST /start
    
    https://cassio-hangman.herokuapp.com/start
	
To make a play send a request to route POST /play/{letter}. Replace the {letter} with a letter of the alphabet.
    
    https://cassio-hangman.herokuapp.com/play/A

The response of each request will be the game information.

    {
        "word": "CLOUD",
        "choices": [
            "R",
            "A",
            "E",
            "I",
            "O",
            "C",
            "D",
            "L",
            "U"
        ],
        "chancesRemaining": 2,
        "status": "SUCCESS"
    }

There are 3 possible status.
SUCCESS and FAIL determine whether you have succeeded or failed in the game and the PLAYING status determines that you still have chances remaining.

You can play the game at https://hangman-front.herokuapp.com
Note: The application is running on Heroku. I notice that it can be very unstable. So is recommended that you use Postman to call the /start and /play endpoints.

List of possible words:
    
    DELL, DELIVER, TECHNOLOGY, CUSTOMER, CLOUD, COMPUTER, SERVER, ADVANCED, 
    STORAGE, SOLLUTIONS, COMMITMENT, DIVERSITY, ENGAGEMENT, COMMUNITY, MILESTONE

Have fun!

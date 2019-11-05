/**
* main Game file
*/

#include "Game.h"

Game::Game()	{}
Game::~Game()	{}

void Game::init(const char* title, int xpos, int ypos, int width, int height, bool fullscreen) 
{
	// Init everything
	int flags = 0; //fullscreen ? SDL_WINDOW_FULLSCREEN : 0;
	if (fullscreen) {
		flags = SDL_WINDOW_FULLSCREEN;
	}

	if (SDL_Init(SDL_INIT_EVERYTHING) == 0) {
		std::cout << "Subsystem initialised!..." << std::endl;
	
		// Create window
		window = SDL_CreateWindow(title, xpos, ypos, width, height, flags);	
		if (window) {
			std::cout << "Window created!" << std::endl;
		}

		// Create renderer
		renderer = SDL_CreateRenderer(window, -1, 0);
		if (renderer) {
			SDL_SetRenderDrawColor(renderer, 255, 255, 255, 255);
			std::cout << "Renderer created!" << std::endl;
		}

		isRunning = true;
	}
	else
	{
		renderer = false;
	}
}

void Game::handleEvents()
{
	// Handle events
	SDL_Event event;
	SDL_PollEvent(&event);

	switch (event.type) {
		// Quitting event
		case SDL_QUIT:
			isRunning = false;
			break;
		default:
			break;	
	}
}

void Game::update() 
{
	
}

void Game::render() 
{
	SDL_RenderClear(renderer);
	// This is where we add stuff to render
	SDL_RenderPresent(renderer);
}

void Game::clean()
{
	SDL_DestroyWindow(window);
	SDL_DestroyRenderer(renderer);
	SDL_Quit();
	std::cout << "Game cleaned." << std::endl;
}
#include <iostream>
#include "Game.h"

Game* game = nullptr;

int main(int argc, const char* argv[])
{

	game = new Game();
	game->init("Shado game engin", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, 800, 600, false);

	while (game->running()) {
		game->handleEvents();
		game->update();
		game->render();
	}

	// After we quit
	game->clean();

	return 0;
}
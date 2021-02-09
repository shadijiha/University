// cpp wallpaperchanger.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <string>
#include <Windows.h>
#include <iostream>
#include <thread>
#include <chrono>
#include <vector>
#include <filesystem>

#if _DEBUG
#define DEBUG_LOG(x)    std::cout << x << std::endl
#define DEBUG_LOG_NO_BREAK(x) std::cout << x
#define DEBUG_WLOG(x)   std::wcout << x << std::endl
#else
#define DEBUG_LOG(x)
#define DEBUG_LOG_NO_BREAK(x)
#define DEBUG_WLOG(x)
#endif

std::vector<std::wstring> getAllWallpapers() {

    namespace fs = std::filesystem;

    std::vector<std::wstring> result;

    std::string path = "C:\\Users\\shadi\\Desktop\\Wallpapers";
    for (const auto& entry : fs::directory_iterator(path)) {
        const auto temp = entry.path();
    	if (!entry.is_directory()) {
            result.push_back(temp.generic_wstring());
    	}			
    }       

    return result;
}

void setWallpaper(int& index)
{
    auto allWallpapers = getAllWallpapers();
    auto currentWP = allWallpapers[index++ % allWallpapers.size()];

    wchar_t* path = (wchar_t*)currentWP.c_str();
	
    int result;
    result = SystemParametersInfo(SPI_SETDESKWALLPAPER, 0, path, SPIF_UPDATEINIFILE);

    if (result)
    {
        DEBUG_LOG_NO_BREAK("Wallpaper set from: ");
        DEBUG_WLOG(currentWP);
    } else
    {
        DEBUG_LOG_NO_BREAK("Wallpaper not set");
        DEBUG_LOG("SPI returned" << result);
    }
}

int main()
{
    using namespace std::chrono_literals;

    std::srand(std::time(nullptr));
	
    unsigned int input = 60 * 5;      // In seconds
    int index = std::rand() % getAllWallpapers().size();
	
    std::chrono::seconds sleeptime(input);

    DEBUG_LOG("*******************************************************");
    DEBUG_LOG("*** Welcome TO 2 monitors same Wallpaper Slideshow! ***");
    DEBUG_LOG("*******************************************************\n");

    DEBUG_LOG("Wallpaper changes every " << (input / 60.0f) << " minutes");

	// Hide the console
#if _DEBUG
    DEBUG_LOG("[DEBUG MODE ACTIVE]");
    ::ShowWindow(::GetConsoleWindow(), SW_SHOW);
#else
    ::ShowWindow(::GetConsoleWindow(), SW_HIDE);
#endif


	/**
	 * Put all windows title Where you don't want the wallpaper to change
	 * 
	 */
	
    std::vector<std::wstring> black_list_windows {L"League of Legends (TM) Client"};
	
	while (true) {

        // Only Change wallpaper if league if not open. This is to avoid the black/white split second screen flicker.
		// In the future must add all games (because I haven't test yet if other games are affected)
        bool canChange = true;
		for (const auto& win : black_list_windows) {
            if (FindWindow(NULL, win.c_str())) {
                canChange = false;
                DEBUG_LOG("Prevented from changing wallpaper because a game window is open!");
            }         
		}

		if (canChange)
			setWallpaper(index);
        
		
        std::this_thread::sleep_for(sleeptime);
	}

}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file

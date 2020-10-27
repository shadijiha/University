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

std::vector<std::wstring> getAllWallpapers() {

    namespace fs = std::filesystem;

    std::vector<std::wstring> result;

    std::string path = "C:\\Users\\shadi\\Desktop\\Wallpapers";
    for (const auto& entry : fs::directory_iterator(path)) {
        const auto temp = entry.path();
        result.push_back(temp.generic_wstring());
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
        std::cout << "Wallpaper set from: ";
        std::wcout << currentWP << std::endl;
    } else
    {
        std::cout << "Wallpaper not set";
        std::cout << "SPI returned" << result << std::endl;
    }
}

int main()
{
    using namespace std::chrono_literals;

    std::srand(std::time(nullptr));
	
    int input = 5;
    int index = std::rand() % getAllWallpapers().size();
	
    std::chrono::minutes sleeptime(input);

    std::cout << "********************************************\n";
    std::cout << "*** Welcome TO 2 monitors same Wallpaper ***\n";
    std::cout << "********************************************\n\n";

    std::cout << "Wallpaper changes every " << input << " minutes\n";

    ::ShowWindow(::GetConsoleWindow(), SW_HIDE);
	
	while (true) {
		
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

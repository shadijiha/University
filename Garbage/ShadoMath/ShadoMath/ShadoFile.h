#ifndef SHADOFILE_H
#define SHADOFILE_H

#include <fstream>
#include <iostream>

class ShadoFile {
private:
	std::ofstream m_file;
	std::string m_filename;
public:

	ShadoFile(std::string filename);

	void write(std::string toWrite);

	void writeToFile(std::string toWrite);

	void close();

	void closeFile();

private:
	bool containsExtention(std::string name);
};


#endif // !SHADOFILE_H

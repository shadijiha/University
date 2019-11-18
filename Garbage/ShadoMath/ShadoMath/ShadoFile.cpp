#include "ShadoFile.h"

ShadoFile::ShadoFile(std::string filename) {

	if (this->containsExtention(filename)) {
		this->m_filename = filename;
		m_file.open(filename);
	}
	else
	{
		this->m_filename = "MissingFileExtention.txt";
		m_file.open(m_filename);
		m_file << "[ERROR]:	Missing file extention!\n";
	}
}

void ShadoFile::write(std::string toWrite) {
	m_file << toWrite;
}

void ShadoFile::writeToFile(std::string toWrite) {
	m_file << toWrite;
}

void ShadoFile::close() {
	m_file.close();
}

void ShadoFile::closeFile() {
	m_file.close();
}


bool ShadoFile::containsExtention(std::string name) {
	for (int i = 0; name[i] != '\0'; i++) {
		if (name[i] == '.') {
			return true;
		}
	}
	return false;
}

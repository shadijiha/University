const mergeFiles = require("merge-files");
const path = require("path");
const fs = require("fs");

// Merge all files
async function compileFiles() {
	const outputPath = __dirname + "/client/compiled_script.js";
	//let inputPathList = []; //fs.readdirSync(__dirname + "/CompiledFiles/");

	priorityList = path.join(__dirname, "/src/priority.txt");
	fs.readFile(priorityList, { encoding: "utf-8" }, async function(err, data) {
		if (!err) {
			// Get files from priority list
			let inputPathList = data.split("\r");
			for (let i = 0; i < inputPathList.length; i++) {
				inputPathList[i] = inputPathList[i].split("\n");
			}

			// Patse files to get only names
			for (let i = 0; i < inputPathList.length; i++) {
				inputPathList[i] = inputPathList[i].join("");
			}

			// Add the directory "D://Folder/Folder//..."
			for (let i = 0; i < inputPathList.length; i++) {
				if (!inputPathList[i].includes(".js")) {
					inputPathList.splice(i, 1);
				}

				inputPathList[i] = __dirname + "/CompiledFiles/" + inputPathList[i];
			}

			// Display message
			const status = await mergeFiles(inputPathList, outputPath);

			console.log(inputPathList);
			if (status) {
				console.log("All Files have been merged successfully");
			} else {
				console.log("Could not merge files! Error!");
			}
		} else {
			console.log(err);
		}
	});
}
compileFiles();

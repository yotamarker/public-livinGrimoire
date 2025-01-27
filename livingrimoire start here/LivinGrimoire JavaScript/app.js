// app.js
const { AbsDictionaryDB, Mutatable, APSay, APVerbatim, GrimoireMemento, Algorithm, Kokoro, Neuron, Skill, DiHelloWorld, Cerabellum, Fusion, Chobits, Brain, DiPrinter } = require('./livingrimoire');

// Create an instance of Brain
const brain = new Brain();

// Add logical and hardware skills
brain.AddLogicalSkill(new DiHelloWorld());
brain.AddHardwareSkill(new DiPrinter());

// Execute the DoIt method
brain.DoIt("hello", "", "");

// Keep the console open
const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question('Press Enter to exit...', () => {
    rl.close();
});

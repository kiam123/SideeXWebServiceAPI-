const fs = require('fs');
var SideeXWebServiceAPI = require('./SideeXWebServiceAPI.js');
var sideeXWebServiceAPI = new SideeXWebServiceAPI();

async function delay(time) {
    await new Promise((resolve) => { setTimeout(resolve, time); });
}

let file = fs.createReadStream('D://web//Selab-web//sideex-webservice-api//inputs.zip');
sideeXWebServiceAPI.runTestSuite("http://127.0.0.1:50000/sideex-webservice", file).then(async(body) => {
    let token = JSON.parse(body).token;
    let flag = false;

    while (!flag) {
        state = await sideeXWebServiceAPI.getState("http://127.0.0.1:50000/sideex-webservice-state", token);
        if (JSON.parse(state).state != "finish") {
            console.log(JSON.parse(state).state);
            await delay(2000);
        } else {
            console.log(JSON.parse(state).state);
            sideeXWebServiceAPI.download("http://127.0.0.1:50000/sideex-webservice-reports", token, 0);
            flag = true;
        }
    }
})
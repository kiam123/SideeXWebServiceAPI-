const fs = require('fs');
const request = require('request');

class SideeXWebServiceAPI {
    constructor() {}

    async runTestSuite(url, file) {
        let body = await new Promise((resolve, reject) => {
            request.post({
                url: url,
                formData: {
                    file: {
                        value: file,
                        options: {
                            filename: file.path,
                            contentType: 'application/zip',
                            knownLength: file.length
                        }
                    }
                },
                timeout: 3000
            }, (error, _response, body) => {
                if (error) {
                    reject(error);
                }
                resolve(body);
            });
        })

        return body;
    }

    async getState(url, token) {
        let body = await new Promise((resolve, reject) => {
            request.get({
                url: url,
                formData: {
                    token: token,
                }
            }, function(error, _response, body) {
                if (error) {
                    console.error(error);
                }
                resolve(body);
            });
        })
        return body;
    }

    async download(url, token, option) {
        let req = request.get({
            url: url,
            formData: {
                token: token,
            }
        });
        req.on('response', async function(res) {
            if (option == 0) {
                res.pipe(fs.createWriteStream('./reports.zip'));
            } else {
                res.pipe(fs.createWriteStream('./logs.zip'));
            }
        });
    }

    async deleteTestSuites(url, token) {
        let body = await new Promise((resolve, reject) => {
            request.post({
                url: url,
                formData: {
                    token: token,
                }
            }, function(error, _response, body) {
                if (error) {
                    console.error(error);
                } else {
                    resolve(body);
                }
            });
        })
        return body;
    }
}

module.exports = SideeXWebServiceAPI;
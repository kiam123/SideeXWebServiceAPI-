import requests
import json
import asyncio
import aiohttp
import os
import re
from SideeXWebServiceAPI import SideeXWebServiceAPI

if __name__=="__main__":
    sideeXWebServiceAPI = SideeXWebServiceAPI()
    ip = "http://127.0.0.1:50000/"
    url = ip + "sideex-webservice"
    file = open('D://web//Selab-web//sideex-webservice-api//inputs-true.zip','rb')
    print(asyncio.run(sideeXWebServiceAPI.runTestSuite(url, file)))

    # asyncio.run(sideeXWebServiceAPI.runTestSuite(url, file, 0))

    # ip = "http://127.0.0.1:50000/"
    # url = ip + "sideex-webservice"
    # file = open('D://web//Selab-web//sideex-webservice-api//inputs.zip','rb')
    # print(asyncio.run(sideeXWebServiceAPI.runTestSuite(url, file, 0)))
    # asyncio.run(sideeXWebServiceAPI.runTestSuite(url, file, 0))

    # token = "0a56ddce-b27c-4474-bf13-892e1bb9418b"
    # print(asyncio.run(sideeXWebServiceAPI.getState('http://127.0.0.1:50000/sideex-webservice-state', token)))

    # token = "0a02b723-d80e-4e80-ad9f-194b97d6ada2"
    # asyncio.run(sideeXWebServiceAPI.download('http://127.0.0.1:50000/sideex-webservice-reports', token, 0))

    # token = "0a02b723-d80e-4e80-ad9f-194b97d6ada2"
    # asyncio.run(sideeXWebServiceAPI.download('http://127.0.0.1:50000/sideex-webservice-logs', token, 1))

    # token = "7d9ee705-edb6-4b16-894c-2133e1a878f7"
    # print(asyncio.run(sideeXWebServiceAPI.deleteReport('http://127.0.0.1:50000/sideex-webservice-delete', token)))
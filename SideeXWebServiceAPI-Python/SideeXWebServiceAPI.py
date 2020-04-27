import requests
import json
import asyncio
import aiohttp
import os
import re

class SideeXWebServiceAPI():
    async def runTestSuite(self, url, file):
        async with aiohttp.ClientSession() as session:
            data = aiohttp.FormData()
            data.add_field('file', file, filename=os.path.basename(file.name), content_type='multipart/form-data')

            async with session.post(url, data = data) as resp:
                return await resp.text()
        
    async def runTestSuiteinWebservice(self, serverURL, file):
        async with aiohttp.ClientSession() as session:
            data = aiohttp.FormData()
            data.add_field('file', file, filename=os.path.basename(file.name), content_type='multipart/form-data')

            async with session.post(serverURL, data = data) as resp:
                return await resp.text()
    
    async def getState(self, serverURL, token):
        async with aiohttp.ClientSession() as session:
            data = aiohttp.FormData()
            data.add_field('token', token, content_type='multipart/form-data')

            async with session.get(serverURL, data = data) as resp:
                return await resp.text()

    async def download(self, serverURL, token, option):
        async with aiohttp.ClientSession() as session:
            data = aiohttp.FormData()
            data.add_field('token', token, content_type='multipart/form-data')

            async with session.get(serverURL, data = data) as resp:
                test = await resp.read()
                if option == 0:
                    with open("./reports.zip", "wb") as f:
                        f.write(test)
                else: 
                    with open("./logs.zip", "wb") as f:
                        f.write(test)

    async def downloadReport(self, serverURL, token):
        async with aiohttp.ClientSession() as session:
            data = aiohttp.FormData()
            data.add_field('token', token, content_type='multipart/form-data')

            async with session.get(serverURL, data = data) as resp:
                test = await resp.read()
                with open("./reports.zip", "wb") as f:
                    f.write(test)

    async def downloadLog(self, serverURL, token):
        async with aiohttp.ClientSession() as session:
            data = aiohttp.FormData()
            data.add_field('token', token, content_type='multipart/form-data')

            async with session.get(serverURL, data = data) as resp:
                test = await resp.read()
                with open("./logs.zip", "wb") as f:
                    f.write(test)

    
    async def deleteReport(self, serverURL, token):
        async with aiohttp.ClientSession() as session:
            data = aiohttp.FormData()
            data.add_field('token', token, content_type='multipart/form-data')

            async with session.post(serverURL, data = data) as resp:
                return await resp.text()
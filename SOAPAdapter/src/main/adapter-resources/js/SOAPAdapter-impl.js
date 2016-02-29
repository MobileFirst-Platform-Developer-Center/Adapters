/**
* Copyright 2016 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


/**
 * @param countryName: a country name like 'United States'.
 * @returns json list of items.
 */
function getCitiesByCountry(countryName) {
	var request = 
	'<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">' +
		'<soap:Body>' +
			'<GetCitiesByCountry xmlns="http://www.webserviceX.NET">' +
				'<CountryName>{0}</CountryName>' +
			'</GetCitiesByCountry>' +
		'</soap:Body>' +
	'</soap:Envelope>';
	
	var input = {
		method: 'post',
		returnedContentType: 'xml',
		path: '/globalweather.asmx',
		body: {
			content: format(request, countryName),
			contentType: 'text/xml; charset=utf-8'
		},
	};
	var result = WL.Server.invokeHttp(input);
	return result.Envelope.Body;
};

function format(msg) {
	var args = Array.prototype.slice.call(arguments, 1);
	var formattedMsg = msg.replace(/{(\d+)}/g, function(m, i) {
		return args[i] || m;
	});
	return formattedMsg;
};

/**
 * @param cityName: a city name like 'Washington'.
 * @param countryName: a country name like 'United States'.
 * @returns json list of items.
 */
function getWeatherInfo(cityName, countryName) {
	var request = 
		'<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">' +
			'<soap:Body>' +
				'<GetWeather xmlns="http://www.webserviceX.NET">' +
					'<CityName>{0}</CityName>' +
					'<CountryName>{1}</CountryName>' +
				'</GetWeather>' +
			'</soap:Body>' +
		'</soap:Envelope>';
	
	var input = {
		method: 'post',
		returnedContentType: 'xml',
		path: '/globalweather.asmx',
		body: {
			content: format(request, cityName, countryName),
			contentType: 'text/xml; charset=utf-8'
		},
	};
	var result = WL.Server.invokeHttp(input);
	return result.Envelope.Body;
};
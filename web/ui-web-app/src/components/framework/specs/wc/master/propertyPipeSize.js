var dat = {
	"wc.create": {
		"numCols": 12/3,
		"url": "/wcms/masters/propertytype-pipesize/_create",
		"tenantIdRequired": true,
		"idJsonPath": "PropertyTypePipeSizes[0].code",
		"useTimestamp": true,
		"objectName": "PropertyTypePipeSize",
		"groups": [
			{
				"label": "wc.create.propertyPipeSize.title",
				"name": "propertyPipeSize",
				"fields": [
					{
						"name": "propertyType",
						"jsonPath": "PropertyTypePipeSize[0].propertyTypeName",
						"label": "wc.create.propertyType",
						"pattern": "",
						"type": "singleValueList",
						"url": "/pt-property/property/propertytypes/_search?|$..name|$..name",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
						"name": "pipeSize",
						"jsonPath": "PropertyTypePipeSize[0].pipeSize",
						"label": "wc.create.pipeSize",
						"pattern": "",
						"type": "singleValueList",
						"url": "/wcms/masters/pipesize/_search?&active=true|$..sizeInMilimeter|$..sizeInMilimeter",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
						{
							"name": "Active",
							"jsonPath": "PropertyTypePipeSize[0].active",
							"label": "Active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"defaultValue":true,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"wc.search": {
		"numCols": 12/3,
		"url": "/wcms/masters/propertytype-pipesize/_search",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "PropertyTypePipeSize",
		"groups": [
			{
				"label": "wc.search.PropertyPipeSize.title",
				"name": "searchDocumentTypeApplicationType",
				"fields": [
					{
						"name": "propertyType",
						"jsonPath": "propertyTypeName",
						"label": "wc.create.propertyType",
						"pattern": "",
						"type": "singleValueList",
						"url": "/pt-property/property/propertytypes/_search?&active=true|$..name|$..name",
						"isRequired": false,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
						"name": "pipeSize",
						"jsonPath": "pipeSize",
						"label": "wc.create.pipeSize",
						"pattern": "",
						"type": "singleValueList",
						"url": "/wcms/masters/pipesize/_search?&active=true|$..sizeInMilimeter|$..sizeInMilimeter",
						"isRequired": false,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
						{
							"name": "Active",
							"jsonPath": "active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		],
		"result": {
			"header": [{label: "wc.create.propertyType"},{label: "wc.create.pipeSize"}, {label: "wc.create.active"}],
			"values": ["propertyTypeName","pipeSize", "active"],
			"resultPath": "PropertyTypePipeSizes",
			"rowClickUrlUpdate": "/update/wc/propertyPipeSize/{id}",
			"rowClickUrlView": "/view/wc/propertyPipeSize/{id}"
			}
	},
	"wc.view": {
		"numCols": 12/3,
		"url": "/wcms/masters/propertytype-pipesize/_search?id={id}",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "PropertyTypePipeSizes",
		"groups": [
			{
				"label": "wc.view.PropertyTypePipeSize.title",
				"name": "viewDocumentTypeApplicationTypes",
				"fields": [
						{
							"name": "propertyTypeName",
							"jsonPath": "PropertyTypePipeSizes[0].propertyTypeName",
							"label": "wc.create.propertyType",
							"pattern": "",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "pipeSize",
							"jsonPath": "PropertyTypePipeSizes[0].pipeSize",
							"label": "wc.create.pipeSize",
							"pattern": "",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "Active",
							"jsonPath": "PropertyTypePipeSizes[0].active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"wc.update": {
		"numCols": 12/3,
		"searchUrl": "/wcms/masters/propertytype-pipesize/_search?id={id}",
		"url":"/wcms/masters/propertytype-pipesize/_update",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "PropertyTypePipeSizes",
		"groups": [
			{
				"label": "wc.update.PropertyTypePipeSize.title",
				"name": "updateDocumentTypeApplicationTypes",
				"fields": [
					{
						"name": "propertyType",
						"jsonPath": "PropertyTypePipeSizes[0].propertyTypeName",
						"label": "wc.create.propertyType",
						"pattern": "",
						"type": "singleValueList",
						"url": "/pt-property/property/propertytypes/_search?|$..name|$..name",
						"isRequired": false,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
						"name": "pipeSize",
						"jsonPath": "PropertyTypePipeSizes[0].pipeSize",
						"label": "wc.create.pipeSize",
						"pattern": "",
						"type": "singleValueList",
						"url": "/wcms/masters/pipesize/_search?&active=true|$..sizeInMilimeter|$..sizeInMilimeter",
						"isRequired": false,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
						{
							"name": "Active",
							"jsonPath": "PropertyTypePipeSizes[0].active",
							"label": "Active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"default": true,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	}
}

export default dat;

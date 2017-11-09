function selectMenuItem(menuIdArr, tree_data) {
	
	var isMenuPresent = false;
	$.each(tree_data, function (index, value){
		if (value.type === 'item') {
			var menuId = value.id.toString();
			if($.inArray(menuId, menuIdArr) !== -1) {
				//alert(menuId);
				value.additionalParameters = {
						'item-selected' : true
				}
				isMenuPresent = true;
			}
		}
		else {
			var flag = selectMenuItem(menuIdArr, value.additionalParameters.children);
			if(flag) {
				value.additionalParameters['item-selected'] = true;
			}
		}
	});
	return isMenuPresent;
}


$(document).ready(function() {
	
	var tree_data = {
			'society' : {
				text : 'Society',
				type : 'folder'
			},
			'reminder' : {
				id : 2,
				text : 'Reminder',
				type : 'item'
			},
			'report' : {
				text : 'Report',
				type : 'folder'
			},
			'setting' : {
				text : 'Setting',
				type : 'folder'
			},
			'notification' : {
				text : 'Notification',
				type : 'folder'
			},
			'maintenance_bill' : {
				text : 'Maintenance Bill',
				type : 'folder'
			},
			'master' : {
				text : 'Master',
				type : 'folder'
			}
		}
		tree_data['society']['additionalParameters'] = {
			'children' : {
				'createUser' : {
					id   : 1,
					text : 'Create User',
					type : 'item'
				},
				'userList' : {
					id : 6,
					text : 'User List',
					type : 'item'
				},
				'member' : {
					id 	 : 19,
					text : 'Member',
					type : 'item'
				}
			}
		}
		tree_data['report']['additionalParameters'] = {
			'children' : {
				'balanceSheet' : {
					id : 3,
					text : 'Balance Sheet',
					type : 'item'
				},
				'incomeAndExpense' : {
					id : 4,
					text : 'Income & Expense',
					type : 'item'
				}
			}
		}
	
		tree_data['maintenance_bill']['additionalParameters'] = {
			'children' : {
				'create' : {
					id : 10,
					text : 'Create',
					type : 'item'
				},
				'view' : {
					id : 11,
					text : 'View',
					type : 'item'
				},
				'head' : {
					id : 14,
					text : 'Head',
					type : 'item'
				},
				'interest' : {
					id : 15,
					text : 'Interest Policy',
					type : 'item'
				},
				'penalty' : {
					id : 16,
					text : 'Penalty Policy',
					type : 'item'
				},
				'rebate' : {
					id : 17,
					text : 'Rebate Policy',
					type : 'item'
				},
				'additional_area' : {
					id : 18,
					text : 'Additional Area',
					type : 'item'
				}
			}
		}
	
		tree_data['setting']['additionalParameters'] = {
			'children' : {
				'generalHead' : {
					id : 7,
					text : 'General Head',
					type : 'item'
				},
				'recordTransaction' : {
					id : 8,
					text : 'Record Transaction',
					type : 'item'
				},
				'societyConfiguration' : {
					id : 9,
					text : 'Society Configuration',
					type : 'item'
				}
			}
		}
	
		tree_data['notification']['additionalParameters'] = {
			'children' : {
				'email' : {
					id : 12,
					text : 'Email',
					type : 'item'
				},
				'message' : {
					id : 13,
					text : 'Message',
					type : 'item'
				}
			}
		}
		tree_data['master']['additionalParameters'] = {
			'children' : {
				'additional_area' : {
					id : 20,
					text : 'Additional Area',
					type : 'item'
				}
			}
		}
	
	var menuIdArr = $("#rights").val().split(",");
	selectMenuItem(menuIdArr, tree_data);
	
	var dataSource1 = function(options, callback) {
		
		var $data = null
		if (!("text" in options) && !("type" in options)) {
			$data = tree_data;// the root tree
			callback({
				data : $data
			});
			return;
		} else if ("type" in options && options.type == "folder") {
			if ("additionalParameters" in options
					&& "children" in options.additionalParameters)
				$data = options.additionalParameters.children || {};
			else
				$data = {}// no data
		}

		if ($data != null)// this setTimeout is only for mimicking some
			// random delay
			setTimeout(function() {
				callback({
					data : $data
				});
			}, parseInt(Math.random() * 500) + 200);
	}

	var sampleData =  {
		'dataSource1' : dataSource1
	}

	$('#tree1').ace_tree({
		dataSource : sampleData['dataSource1'],
		multiSelect : true,
		cacheItems : true,
		'open-icon' : 'ace-icon tree-minus',
		'close-icon' : 'ace-icon tree-plus',
		'itemSelect' : true,
		'folderSelect' : false,
		'selected-icon' : 'ace-icon fa fa-check',
		'unselected-icon' : 'ace-icon fa fa-times',
		loadingHTML : '<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>'
	});
	
	$("#societyUserSubmitBtn").click(function() {
		
		var isValid = true;
		
		var userName = $("#userName").val();
		if(userName == "") {
			showValidationMsg("User Name","Please enter value for user name.");
			isValid = false;
		}
			
		
		var userPassword = $("#userPassword").val();
		if(userPassword == "") {
			showValidationMsg("Password","Please enter value for password.");
			isValid = false;
		}
			
		
		var firstName = $("#firstName").val();
		if(firstName == ""){
			showValidationMsg("First Name","Please enter value for First Name.");
			isValid = false;
		}
			
		
		var lastName = $("#lastName").val();
		if(lastName == "") {
			showValidationMsg("Last Name","Please enter value for Last Name.");
			isValid = false;
		}
			
		
		var contactNumber = $("#contactNumber").val();
		if(contactNumber == ""){
			showValidationMsg("Contact Number","Please enter value for Conatct Number.");
			isValid = false;
		}
			
		
		var emailId = $("#emailId").val();
		if(emailId == "") {
			showValidationMsg("Email Id","Please enter value for email Id.");
			isValid = false;
		}
		if(!validateEmail(emailId)) {
			showValidationMsg("Email Id","Please enter correct format for mail id.");
			isValid = false;
		}
		
		if(isValid) {
			$("#tree1").find(".tree-selected").map(function() {
				$(this).find("input[type=checkbox]").attr("checked", true);
			});
			$("#rights").remove();
		}
		
		return isValid;
	});
});

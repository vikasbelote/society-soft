<tr>
	<td class="center">
		<div class="action-buttons">
			<a href="#" class="green bigger-140 show-details-btn"
				title="Show Details"> <i
				class="ace-icon fa fa-angle-double-down"></i> <span class="sr-only">Details</span>
			</a>
		</div>
	</td>

	<td>${ personDomain.firstName }</td>
	<td>${ personDomain.middleName }</td>
	<td>${ personDomain.lastName }</td>
	<td>${ personDomain.contactNumber }</td>
	<td>${ personDomain.emailId }</td>

	<td>
		<div class="hidden-sm hidden-xs btn-group">

			<a id="editMemberAnchor" href="#modal-form" data-toggle="modal"
				class="btn btn-xs btn-info editMember"> <i
				class="ace-icon fa fa-pencil bigger-120"></i>
			</a>

			<button class="btn btn-xs btn-danger deleteMember">
				<i class="ace-icon fa fa-trash-o bigger-120"></i>
			</button>

		</div>

		<div class="hidden-md hidden-lg">
			<div class="inline pos-rel">
				<button class="btn btn-minier btn-primary dropdown-toggle"
					data-toggle="dropdown" data-position="auto">
					<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
				</button>

				<ul
					class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">

					<li><a href="#" class="tooltip-success" data-rel="tooltip"
						title="Edit"> <span class="green"> <i
								class="ace-icon fa fa-pencil-square-o bigger-120"></i>
						</span>
					</a></li>

					<li><a href="#" class="tooltip-error" data-rel="tooltip"
						title="Delete"> <span class="red"> <i
								class="ace-icon fa fa-trash-o bigger-120"></i>
						</span>
					</a></li>
				</ul>
			</div>
		</div>
	</td>
</tr>

<tr class="detail-row">
	<td colspan="8">
		<div class="table-detail">
			<div class="row">

				<div class="col-xs-12 col-sm-7">
					<div class="space visible-xs"></div>

					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">First Name</div>

							<div class="profile-info-value">
								<input type="hidden" value="${ personDomain.firstName }" name="memberFirstNameArr" />
								<span>${ personDomain.firstName }</span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">Middle Name</div>

							<div class="profile-info-value">
								<input type="hidden" value="${ personDomain.middleName }" name="memberMiddleNameArr" />
								<span>${ personDomain.middleName }</span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">Last Name</div>

							<div class="profile-info-value">
								<input type="hidden" value="${ personDomain.lastName }" name="memberLastNameArr" />
								<span>${ personDomain.lastName }</span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">Mobile No</div>

							<div class="profile-info-value">
								<input type="hidden" value="${ personDomain.contactNumber }" name="memberContactNumberArr" />
								<span>${ personDomain.contactNumber }</span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">Email Id</div>

							<div class="profile-info-value">
								<input type="hidden" value="${ personDomain.emailId }" name="memberEmailIdArr" />
								<span>${ personDomain.emailId }</span>
							</div>
						</div>
						
						<div class="profile-info-row">
							<div class="profile-info-name">Wing Number</div>

							<div class="profile-info-value">
								<input type="hidden" value="${ personDomain.wingNumber }" name="wingNumberArr" />
								<span>${ personDomain.wingNumber }</span>
							</div>
						</div>
						
						<div class="profile-info-row">
							<div class="profile-info-name">Flat Number</div>

							<div class="profile-info-value">
								<input type="hidden" value="${ personDomain.flatNumber }" name="flatNumberArr" />
								<span>${ personDomain.flatNumber }</span>
							</div>
						</div>
						
						<div class="profile-info-row">
							<div class="profile-info-name">Square Feet</div>

							<div class="profile-info-value">
								<input type="hidden" value="${ personDomain.squareFeet }" name="squareFeetArr" />
								<span>${ personDomain.squareFeet }</span>
							</div>
						</div>
						
						<div class="profile-info-row">
							<div class="profile-info-name">Additional Area</div>

							<div class="profile-info-value">
								<input type="hidden" value="${ personDomain.additionalAreaId }" name="additionalAreaArr" />
								<span>${ personDomain.additionalAreaName }</span>
							</div>
						</div>
						
					</div>
				</div>

				<div class="col-xs-12 col-sm-3">
					<div class="space visible-xs"></div>
					<h4 class="header blue lighter less-margin">Send message</h4>

					<div class="space-6"></div>

					<form>
						<fieldset>
							<textarea class="width-100" resize="none" placeholder="Type here"></textarea>
						</fieldset>

						<div class="hr hr-dotted"></div>

						<div class="clearfix">
							<label class="pull-left"> <input type="checkbox"
								class="ace" /> <span class="lbl"> Email me a copy</span>
							</label>

							<button
								class="pull-right btn btn-sm btn-primary btn-white btn-round"
								type="button">
								Submit <i
									class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</td>
</tr>



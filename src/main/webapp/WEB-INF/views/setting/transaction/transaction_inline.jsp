<script type="text/javascript">
	(function($){
		$.widget( "custom.catcomplete", $.ui.autocomplete, {
			_create: function() {
				this._super();
				this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
				//this.element.attr("data-dId", "123");
			},
			_renderMenu: function( ul, items ) {
				var that = this,
				currentGeneralHeadName = "";
				$.each( items, function( index, item ) {
					var li;
					if ( item.generalHeadName != currentGeneralHeadName ) {
						ul.append( "<li class='ui-autocomplete-category'>" + item.generalHeadName + "</li>" );
						currentGeneralHeadName = item.generalHeadName;
					}
					li = that._renderItemData( ul, item );
					li.attr("data-descId", item.descId);
					li.attr("data-generalHeadId", item.generalHeadId);
					if ( item.generalHeadName ) {
						li.attr( "aria-label", item.generalHeadName + " : " + item.label );
					}
				});
			}
		});

		/* var data = [
					{descId : 1, label: "d1",  generalHeadId : 99999, generalHeadName: "" },
					{descId : 2, label: "d1",  generalHeadId : 99999, generalHeadName: "" },
					{descId : 3, label: "d3",  generalHeadId : 99999, generalHeadName: "" },
					{descId : 4, label: "SC1", generalHeadId : 1, generalHeadName: "Share Capital" },
					{descId : 5, label: "SC2", generalHeadId : 1, generalHeadName: "Share Capital" },
					{descId : 6, label: "SC3", generalHeadId : 1, generalHeadName: "Share Capital" },
					{descId : 7, label: "FA1", generalHeadId : 6, generalHeadName: "Fixed Assets" },
					{descId : 8, label: "FA2", generalHeadId : 6, generalHeadName: "Fixed Assets" },
					{descId : 9, label: "FA3", generalHeadId : 6, generalHeadName: "Fixed Assets" }
				]; */
		var data = JSON.parse('${tranDescDomainList}');

		$( "#transactionDescription" ).catcomplete({
			delay: 0,
			source: data
		});
	})(jQuery);
</script>

<script src="./assets/inline-script/setting/transaction.js"></script>
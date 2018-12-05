<html>
	<head>
		<link rel="stylesheet" href="/css/jquery-ui.min.css" />
		<link rel="stylesheet" href="/css/reviews.css" />
		<script src="/js/jquery-3.2.1.min.js"></script>
		<script src="/js/jquery-ui.min.js"></script>

		<title>Reviews Manager</title>

		<script>
			$(function() {
				$('#productId').autocomplete({
					source: function(request, response) {
						$.ajax({
							url: '/reviews-data',
							dataType: 'json',
							data: {
								term: request.term + '%'
							},
							success: function(data) {
								var list = data.map(function(e){
									return e.productId;
								});

								response(list);
							}
						});
					},
					minLength: 1,
					select: function(event, ui) {
						$.ajax({
							url: '/review/' + ui.item.value,
							dataType: "json",
							success: function(data) {
								if (data && data.productId) {
									$('.review-div').removeClass('hide');

									$('#productIdLabel').html(data.productId);
									$('#numberLabel').html(data.reviewsNumber);
									$('#scoreLabel').html(data.averageScore);

									$('.review-div button').off('**');

									$('.review-div button').on(
										'click',
										function() {
											$.ajax({
												url: '/review/' + ui.item.value,
												dataType: 'json',
												method: 'DELETE',
												success: function(data) {
													if (data) {
														$('.review-div').addClass('hide');

														window.location.reload();
													}
												}
											});
										}
									);
								}
							}
						});
					}
				});
				
				$('#createReviewButton').on(
					'click',
					function() {
						var dialog = $('<div id="dialog"></div>')
							.html('<form id="reviewForm">' +
								  '<fieldset><table><tr><td>' +
								  '<label for="productId">Product ID:</label></td>' +
								  '<td><input type="text" id="productIdField" value="" class="text ui-widget-content ui-corner-all" /></td></tr>' +
								  '<tr><td><label for="cardNumber">Reviews Number:</label></td>' +
								  '<td><input type="text" id="reviewsNumberField" value="" class="text ui-widget-content ui-corner-all" /></td></tr>' +
								  '<tr><td><label for="cardNumber">Average Score:</label></td><td>' +
								  '<input type="text" id="averageScoreField" value="" class="text ui-widget-content ui-corner-all" /></td></tr>' +
								  '</table></fieldset></form>')
							.dialog({
								autoOpen: false,
								modal: true,
								height: 230,
								width: 500,
								title: "Create new Review",
								buttons: [
									{
										text: "Save",
										click: function () {
											var data = {
												productId: $('#productIdField').val(),
												reviewsNumber: $('#reviewsNumberField').val(),
												averageScore: $('#averageScoreField').val()
											};

											$.ajax({
												url: '/review',
												type: 'POST',
												contentType: 'application/json',
												data: JSON.stringify(data),
												success: function() {
													$('#dialog').dialog('destroy').remove();

													window.location.reload();
												},
												error: function() {
													alert('An error occured, check your review fields or review service logs.')
												}
											});
										}
									},
									{
										text: "Cancel",
										click: function() {
											$(this).dialog('destroy').remove()
										}
									},
								]
							});

						dialog.dialog('open');
					}
				);
			});
			
		</script>
	</head>
	<body>
		<div class="main-div">
			<br>
			<h4>Just start typing to get a hint or create a new review :)</h4>
			<h5>There <#if (count > 1)>are <#else>is</#if> <b>${count}</b><#if (count > 1)> reviews <#else> review</#if> in the database.</h5>

			<label for="productId"><b>Product ID:</b></label>
			<input class="product-id" id="productId" type="text">&nbsp;
			<button id="createReviewButton">Create Review</button>

			<div class="review-div hide">
				<b>Product ID:</b>&nbsp;<span id="productIdLabel"></span>,&nbsp;
				<b>Reviews number:</b>&nbsp;<span id="numberLabel"></span>,&nbsp;
				<b>Average score:</b>&nbsp;<span id="scoreLabel"></span>&nbsp;

				<button>Delete Review</button>
			</div>
		</div>
	</body>
</html>
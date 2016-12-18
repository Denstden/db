$(document).ready(function () {
	$('#createdAt').val(new Date());
	$('#btn').bind('click', function () {
		$.ajax({
				url: '/databases/tables/intersection' + buildParams(),
				type: "GET",
				headers: {
			'Content-Type': 'application/json'
		},
				success: function (res) {
					console.log(res);
					$('#tableName').css({display : "block"});
					$('#resultTable').css({display : "block"});
					$('#tableName').text(res.tableName);
					var header = $('<tr></tr>');
					for (var i = 0; i < res.columns.length; i++) {
						header.append($('<th>' + res.columns[i].columnName + '</th>'));
					}
					$('#resultTable').append(header);
					for (var j = 0; j < res.records.length; j++) {
						var row = $('<tr></tr>');
						row.append($('<td>' + res.records[j].record['ID'] + '</td>'));
						row.append($('<td>' + res.records[j].record['Name'] + '</td>'));
						$('#resultTable').append(row);
					}
					$('#wrapper').hide();
				}
			}
		)
	});

	var buildParams = function () {
		return "?dbName1=" + $("#dbName1").val() + "&dbName2=" + $("#dbName2").val() + "&tableName1=" + $("#tableName1").val() + "&tableName2=" + $("#tableName2").val();
	}
});

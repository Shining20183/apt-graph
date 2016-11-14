function draw_graph(json_data){
	var data = json_data;
	var links = [];
	for (var n= 0; n < data.length; n++){
		for (var key in data[n].hashMap){
			var id_regex = /\d+/g;
			var url_regex = /https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)/g;
			var id_matches = key.match(id_regex);
			var url_matches = key.match(url_regex);
			console.log(key);
			console.log(url_matches);
			var source_name = url_matches[0];
			var source_id = id_matches[0];
			var target, similarity;
			var value = data[n].hashMap[key];
			if (value.length == 0){
				target = [source_id, source_name];
				similarity = 0;
				links.push({"source": [source_id, source_name], "target": target, "value": similarity});
			} else {
				for (var m= 0; m < value.length; m++){
					target = [value[m].node.id, value[m].node.value.url];
					similarity = value[m].similarity;
					links.push({"source": [source_id, source_name], "target": target, "value": similarity});
				}
			}
		}
	}
	//console.log(links);
	var nodes = {};
	
	function findLinkToTarget(name){
		for (var n= 0; n < links.length; n++){
			
		}
	}
	
	// Compute the distinct nodes from the links.
	links.forEach(function(link) {
		link.source = nodes[link.source] || 
			(nodes[link.source] = {name: link.source});
		link.target = nodes[link.target] || 
			(nodes[link.target] = {name: link.target});
		link.value = +link.value;
	});
	
	//console.log(nodes);
	
	var graph_div = document.getElementById("graph");
	var width = window.innerWidth; 
	var	height = window.innerHeight; 
	var width_button = 200;
	
	var force = d3.layout.force()
		.nodes(d3.values(nodes))
		.links(links)
		.size([width , height])
		.linkDistance(500)
		.charge(-150)
		.on("tick", tick)
		.start();

/*	var buttons = d3.select("body").select("#container").select("#parent").select("#side_bar").append("svg")
		//.attr("class", "container_buttons")
//		.attr("width", width_button)
		.attr("height", height)
		.append("g");
	var prune_button = buttons.append("rect")       // attach a rectangle
		.attr("class", "button")
//		.attr("x", 20)         // position the left of the rectangle
	    .attr("y", 50)          // position the top of the rectangle
		.attr("height", 50)    // set the height
		.attr("width", 100)     // set the width
		.attr("rx", 10)         // set the x corner curve radius
		.attr("ry", 10);        // set the y corner curve radius
*/
	var svg = d3.select("body").select("#container").select("#parent").select("#graph").append("svg")
		.attr("width", "100%")
		.attr("height", height);
	
	// build the arrow.
	svg.append("svg:defs").selectAll("marker")
		.data(["end"])      // Different link/path types can be defined here
	.enter().append("svg:marker")    // This section adds in the arrows
		.attr("id", String)
		.attr("viewBox", "0 -5 10 10")
		.attr("refX", 15)
		.attr("refY", -1.5)
		.attr("markerWidth", 6)
		.attr("markerHeight", 6)
		.attr("orient", "auto")
	.append("svg:path")
		.attr("d", "M0,-5L10,0L0,5");
	
	// add the links and the arrows
	var path = svg.append("svg:g").selectAll("path")
		.data(force.links())
	.enter().append("svg:path")
	//    .attr("class", function(d) { return "link " + d.type; })
		.attr("class", "link")
		.attr("marker-end", function(d) {if (d.value == 0){
											return "";
										} else {
											return "url(#end)"}});
	
	// define the nodes
	var node = svg.selectAll(".node")
		.data(force.nodes())
	.enter().append("g")
		.attr("class", "node")
		.on("mouseover", function(d) {
			var g = d3.select(this); // The node
			// The class is used to remove the additional text later
			var target = nodes[d.name];
			var info = g.append('text')
				.classed('info', true)
				.attr('x', 20)
				.attr('y', 10)
				.attr("font-size","30px")
				.text(function(d) { return d.name[1]; });
		})
		.on("mouseout", function() {
			// Remove the info text on mouse out.
			d3.select(this).select('text.info').remove();
		})
		.call(force.drag);
	
	// add the nodes
	node.append("circle")
		.attr("r", 5);
	
	// add the text 
	node.append("text")
		.attr("x", 12)
		.attr("dy", ".35em")
		.attr("font-size", "10xp")
		.text(function(d) { return d.name[0]; });
	
	// add the curvy lines
	function tick() {
		path.attr("d", function(d) {
			var dx = d.target.x - d.source.x,
				dy = d.target.y - d.source.y,
				dr = Math.sqrt(dx * dx + dy * dy);
			return "M" + 
				d.source.x + "," + 
				d.source.y + "A" + 
				dr + "," + dr + " 0 0,1 " + 
				d.target.x + "," + 
				d.target.y;
		});
	
		node
			.attr("transform", function(d) { 
			return "translate(" + d.x + "," + d.y + ")"; });
	}

	prune_button.on("click",function(){watchNode(focus);});
}
(function() {
    window.answerGraph = function(containerId, data) {
        d3.select("#"+containerId).text("");

        var svg = d3.select("#"+containerId).append("svg")
            .attr("height", "400")
            .attr("width", "800");

        var box = {ymin: 1, ymax: svg.attr("height") - 50, xmin:0, xmax: svg.attr("width")};

        var x = d3.scaleBand()
            .domain(d3.range(data.length))
            .range([box.xmin, box.xmax])
            .paddingInner(0.3)
            .paddingOuter(0.3);
        var y = d3.scaleLinear()
            .domain([0,100])
            .range([box.ymin, box.ymax]);

        var n_y = d3.scaleLinear()
            .domain([0,100])
            .range([box.ymax, box.ymin]);

        var numberOfGridlines = 5;
        var gy = d3.scaleLinear().domain([0, numberOfGridlines]).range([box.ymin, box.ymax]);
        var gridlines = svg.selectAll(".gridline")
            .data(d3.range(numberOfGridlines))
            .enter().append("line")
                .attr("class", ".gridline")
                .attr("stroke", "gainsboro")
                .attr("y1", function(d) {return gy(d);})
                .attr("y2", function(d) {return gy(d);})
                .attr("x1", box.xmin)
                .attr("x2", box.xmax);

        //axis line
        svg.append("line")
            .attr("x1", box.xmin)
            .attr("x2", box.xmax)
            .attr("stroke", "#999")
            .attr("y1", n_y(0))
            .attr("y2", n_y(0))

        var link = svg.selectAll(".link")
            .data(data)
            .enter().append("a")
                .attr("class", "link")
                .attr("xlink:href", function(d) {return "/Roles/"+d[0]});

        // axis label
        link.append("text")
                .attr("class", "axisLabel")
                .attr("text-anchor","middle")
                .attr("x", function(d,i) {return x(i) + 0.5* x.bandwidth(); })
                .attr("y", box.ymax + 25)
                .attr("fill", "#005ea5")
                .attr("stroke", "#005ea5")
                .style("font-size", "0.9em")
                .text(function(d) {return d[0]; });


        //bar
        var rect = link.append("rect")
                .attr("class", "bar")
                .attr("x", function(d,i) {return x(i);})
                .attr("y", box.ymax)
                .attr("width", function () {return x.bandwidth()})
                .attr("height", 0)
                .style("fill", "hsla(229, 68%, 55%, 0.7)");


        rect.transition()
            .delay(function (d, i) { return 200 * i + 600; })
            .duration(650)
            .ease(d3.easeQuadOut)
            .attr("y", function(d,i) {return n_y(d[1]);})
            .attr("height", function(d) {return y(d[1])-y(0)})

        //valueLabel
        var valueLabel = link.append("text")
                .attr("class", "valueLabel")
                .attr("text-anchor", "middle")
                .style("font-size", "0.9em")
                .style("opacity", 0)
                .attr("x", function(d,i) {return x(i) + 0.5* x.bandwidth(); })
                .attr("y", function(d){return Math.max(15, n_y(d[1]) - 40); })
                .text(function(d) {return d[1] + "%"});

        valueLabel.transition()
                    .delay(function (d, i) { return 200 * i + 1000; })
                    .duration(650)
                    .ease(d3.easeQuadOut)
                    .style("opacity", 1)
                    .attr("y", function(d){return Math.max(15, n_y(d[1]) - 10); });


    };

})();
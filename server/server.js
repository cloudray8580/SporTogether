var express   =    require("express");
var mysql     =    require('mysql');
var app       =    express();

var pool      =    mysql.createPool({
    connectionLimit : 100, //important
    host     : 'localhost',
    user     : 'root',
    password : 'aa930412',
    database : 'sport',
    debug    :  false
});


function handle_database(req,res) {
    
    pool.getConnection(function(err,connection){
        if (err) {
          res.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }   

        console.log('connected as id ' + connection.threadId);
     
        connection.query(req, function(err,rows){
            connection.release();
            if(!err) {
                res.json(rows);
            }           
        });

        connection.on('error', function(err) {      
              res.json({"code" : 100, "status" : "Error in connection database"});
              return;     
        });
  });
}

console.log("Server Warming Up >>>>>>>>>>>>");
app.get("/",function(req,res){
    res.send("Welcome");
});

app.get("/user/add",function(req,res){

    var json = JSON.parse(req.query.request);

    var request  = "INSERT INTO user";
    request += " (user_id, user_name, user_pwd, user_nickname, user_gender, user_age, user_phone, user_selfintro)";
    request += " VALUES (";
    request += json.request[0].user_id+",";
    request += json.request[0].user_name+",";
    request += json.request[0].user_pwd+",";
    request += json.request[0].user_nickname+",";
    request += json.request[0].user_gender+",";
    request += json.request[0].user_age+",";
    request += json.request[0].user_phone+",";
    request += json.request[0].user_selfintro+")";

    handle_database(request, res);

});

app.get("/user/update",function(req,res){
    var request = "UPDATE user SET "+req.query.update_field+"='"+req.query.update_value
                  + "' WHERE user_id='"+req.query.user_id+"'";
    handle_database(request, res);
});

app.get("/user/get",function(req,res){

    var request  = "SELECT * FROM user WHERE user_id ='"+req.query.user_id+"'";

    handle_database(request, res);
});

app.get("/user/login",function(req,res){
    
    var request  = "SELECT user_pwd FROM user WHERE user_id ='"+req.query.user_id+"'";

    pool.getConnection(function(err,connection){
        if (err) {
          res.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }   

        console.log('connected as id ' + connection.threadId);
     
        connection.query(request, function(err,rows){
            connection.release();
            if(!err) {
                //verify the pwd
                if(rows.length>0){
                    if(rows[0].user_pwd == req.query.user_pwd){
                      res.json({"code": 200, "status" : "Login Successfully"});
                    }
                    else{
                      res.json({"code": 201, "status" : "Wrong Password"});

                    }
                }
                else{
                      res.json({"code": 202, "status" : "No Such User"});
                }
            }           
        });

        connection.on('error', function(err) {      
              res.json({"code" : 100, "status" : "Error in connection database"});
              return;     
        });
  });
});

app.get("/events/add",function(req,res){

      var json = JSON.parse(req.query.request);

      var request  = "INSERT INTO event";
      request += " (event_id, event_title, event_location, event_location_x, event_location_y, event_description, event_sporttype, event_requirednum, event_currentnum, event_time, event_creatorid, event_creatorname, event_creatorphone, event_isvalid)";
      request += " VALUES (";
      request += json.request[0].event_id+",";
      request += json.request[0].event_tile+",";
      request += json.request[0].event_location+",";
      request += json.request[0].event_location_x+",";
      request += json.request[0].event_location_y+",";
      request += json.request[0].event_description+",";
      request += json.request[0].event_sporttype+",";
      request += json.request[0].event_requirednum+",";
      request += json.request[0].event_currentnum+",";
      request += json.request[0].event_time+",";
      request += json.request[0].event_creatorid+",";
      request += json.request[0].event_creatorname+",";
      request += json.request[0].event_creatorphone+",";
      request += json.request[0].event_isvalid+")";

      handle_database(request, res);

});
app.get("/events",function(req,res){

      var request  = "SELECT * FROM event";
      handle_database(request, res);

});

app.get("/events/join",function(req,res){

      var request  = "INSERT INTO joinevent (join_user_id, join_event_id) VALUES ('"+req.query.user_id+"','"+req.query.event_id+"'')";
      handle_database(request, res);

});

app.get("/events/local",function(req,res){

      var request = "SELECT * FROM event";

      pool.getConnection(function(err,connection){
        
        if (err) {
          res.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }   

        console.log('connected as id ' + connection.threadId);
     
        connection.query(request, function(err,rows){
            connection.release();
            if(!err) {

                var minID;
                var minDist=1000000000;

                var i = 0;
                while(i<rows.length){
                  var x = rows.event_location_x - req.query.locationX;
                  var y = rows.event_location_y - req.query.locationY;
                  if(x*x+y*y<minDist){
                    minID = i;
                    minDist = x*x + y*y;
                  }
                  
                }
                res.json(rows[minID]);
            }           
        });

        connection.on('error', function(err) {      
              res.json({"code" : 100, "status" : "Error in connection database"});
              return;     
        });
      });


});

app.get("/events/suitable",function(req,res){

      var request = "SELECT * FROM event WHERE event_sporttype='"+req.query.sportType+"'";

});

function pad2(n) {  // always returns a string
      return (n < 10 ? '0' : '') + n;
}

function getCurrentTime(){

      var d = new Date();

      var second = d.getSeconds();
      var minute = d.getMinutes();
      var hour = d.getHours();
      var day = d.getDate();
      var month = d.getMonth() + 1;
      var year = d.getFullYear();

      var current_time = year+"-"+pad2(month)+"-"+pad2(day)+" "+pad2(hour)+":"+pad2(minute)+":"+pad2(second);

      return current_time;
}


app.get("/events/history",function(req,res){

      var time = getCurrentTime();

      var request = "SELECT * FROM joinevent j JOIN event e ON j.join_event_id = e.event_id WHERE j.join_user_id="+req.query.user_id;
      request += " AND e.event_time<'"+time+"'";

      handle_database(request, res);


});

app.get("/events/current",function(req,res){

      var time = getCurrentTime();

      var request = "SELECT * FROM joinevent j JOIN event e ON j.join_event_id = e.event_id WHERE j.join_user_id="+req.query.user_id;
      request += " AND e.event_time>'"+time+"'";

      handle_database(request, res);

});


app.get("/app_request",function(req,res){
        handle_database(req,res);
});


//server
var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("Server address http://%s:%s", host, port)

})


/**
 * Created by charques on 22/8/16.
 */

// =======================
// get the packages we need ============
// =======================
var express     = require('express');
var app         = express();
var bodyParser  = require('body-parser');
var morgan      = require('morgan');
var mongoose    = require('mongoose');
var helmet      = require('helmet');
var cors        = require('cors');

var config = require('./app/config/config'); // get config file

// =======================
// configuration =========
// =======================
global.__base = __dirname + '/'; // for better manage of module loads
var port = process.env.PORT || config.defaultPort;

var MONGO_DB;
var DOCKER_DB = process.env.MONGO_PORT;
console.log('**** ' + DOCKER_DB);
if ( DOCKER_DB ) {
    MONGO_DB = DOCKER_DB.replace( 'tcp', 'mongodb' ) + '/so9pojyN';
} else {
    MONGO_DB = process.env.MONGODB;
}
mongoose.connect(MONGO_DB); // connect to database
app.set('secret-key', config.secret); // secret variable

// use body parser so we can get info from POST and/or URL parameters
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// use helmet security settings
app.use(helmet.noCache());
app.use(helmet.hidePoweredBy());

// use morgan to log requests to the console
app.use(morgan('dev'));

// cors configuration
var corsOptions = {
    origin: 'http://macrolayer.io'
};
cors(corsOptions);

// TODO test if error handling works
// TODO Eureka
// TODO tests

// =======================
// error handling ========
// =======================
var errorHandling = require ('./app/middlewares/error');
app.use(errorHandling.logErrors);
app.use(errorHandling.clientErrorHandler);
app.use(errorHandling.errorHandler);

// =======================
// routes ================
// =======================
app.use(config.baseUrl, require('./app/controllers'));

// =======================
// start the server ======
// =======================
app.listen(port);
console.log('Magic happens at http://localhost:' + port + config.baseUrl);

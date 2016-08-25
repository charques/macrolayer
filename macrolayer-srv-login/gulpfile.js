'use strict';

var gulp = require('gulp');
var nodemon = require('gulp-nodemon');
var jshint = require('gulp-jshint')


gulp.task('default', ['lint','nodemon'], function () {
});

gulp.task('lint', function () {
    gulp.src('./app/**/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('jshint-stylish'));
})

gulp.task('nodemon', function (cb) {

    var started = false;

    return nodemon({
        script: 'server.js',
        tasks: ['lint']
    }).on('start', function () {
        // to avoid nodemon being started multiple times
        // thanks @matthisk
        if (!started) {
            cb();
            started = true;
        }
    });
});
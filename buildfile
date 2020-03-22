#
# Generated by Buildr 1.4.21, changed by kredel to his liking
#
# $Id$
#

#ENV['JAVA_OPTS'] ||= '-Xms1g -Xmx1g'

require 'buildr/groovy'

# Version number for this release
VERSION_NUMBER = "2.6." + `svnlook youngest /home/SUBVERSION/jas`.to_s.chomp
# Group identifier for your projects
GROUP = "JavaAlgebraSystem"
COPYRIGHT = "Copyright (c) 2005-2020 by Heinz Kredel"

# Specify Maven 2.0 remote repositories here, like this:
#repositories.remote << "http://repo1.maven.org/maven2"
#repositories.remote << "file:/home/kredel/java/lib"

#repositories.local = "/home/kredel/java/lib"

#LOG4J = "log4j:log4j:jar:1.2.17"
#url1 = "file:/home/kredel/java/lib/log4j.jar"
#download(artifact(LOG4J)=>url1)

LOG4Ja = "org.apache.logging.log4j:log4j-api:jar:2.5"
LOG4Jc = "org.apache.logging.log4j:log4j-core:jar:2.5"
LOG4J1 = "org.apache.logging.log4j:log4j-1.2-api:jar:2.5"
url1a = "file:/home/kredel/java/lib/log4j-api.jar"
download(artifact(LOG4Ja)=>url1a)
url1c = "file:/home/kredel/java/lib/log4j-core.jar"
download(artifact(LOG4Jc)=>url1c)
url11 = "file:/home/kredel/java/lib/log4j-1.2-api.jar"
download(artifact(LOG4J1)=>url11)

#JUNIT = "junit:junit:jar:3.8"
JUNIT = "junit:junit:jar:4.12"
url2 = "file:/home/kredel/java/lib/junit.jar"
download(artifact(JUNIT)=>url2)


jas_layout = Layout.new
jas_layout[:source, :main, :java] = 'src'
jas_layout[:source, :test, :java] = 'trc'
#jas_layout[:target, :main, :classes] = 'classes'
#jas_layout[:target, :test, :classes] = 'classes'
#jas_layout[:reports, :target, :test] = 'test'


desc "The Java Algebra System (JAS) project"
define "jas", :layout=>jas_layout do

  project.version = VERSION_NUMBER
  project.group = GROUP
  manifest["Implementation-Vendor"] = COPYRIGHT
  puts "running buildr for: " + project.group + "-" + project.version

  compile.with LOG4Ja, LOG4Jc, LOG4J1, JUNIT
  #test.compile.with JUNIT
  #test.with LOG4Ja, LOG4Jc, LOG4J1, JUNIT
  test.using :junit, :fail_on_failure=>false
  #test.include '*Test'
  test.include 'edu.jas.arith.*Test'
  run.using :main => [ "edu.jas.application.RunGB" , "seq", "examples/trinks7.jas", "2", "nolog" ]

  repositories.release_to[:url] = 'file:/home/kredel/jas/target/repo'
  package(:jar).with(:manifest=>_('GBManifest.MF')).tap do |path|
     path.include _('README')
     path.include _('COPYING*')
     path.include( _('../lib/log4j-api.jar'), :as => "lib/log4j-api.jar")
     path.include( _('../lib/log4j-core.jar'), :as => "lib/log4j-core.jar")
     path.include( _('../lib/log4j-1.2-api.jar'), :as => "lib/log4j-1.2-api.jar")
     path.include( _('../lib/junit.jar'), :as => "lib/junit.jar")
     #meta_inf << file('DISCLAIMER') << file('NOTICE')
  end
  package(:zip, :classifier=>'javadoc').tap do |path|
     path.include( _('target/doc'), :as=> "doc" )
     path.include _('*.html')
     path.include _('*.css')
     path.include _('images')
     path.include _('README')
     path.include _('COPYING*')
  end
  package(:zip, :classifier=>'sources').tap do |path|
     path.include _(:source, :main, :java)
     path.include _(:source, :test, :java)
     path.include _('README')
     path.include _('COPYING*')
  end

  #puts 'com dep         ' + compile.dependencies.map{ |d| d.to_hash}.join(" : ")
  #puts 'test dep        ' + test.dependencies.map{ |d| d.to_hash}.join(" : ")
  #puts 'Compiling from  ' + compile.source.to_s
  #puts 'Compiling to    ' + compile.target.to_s
  #puts 'ComTesting from ' + test.compile.source.to_s
  #puts 'ComTesting to   ' + test.compile.target.to_s
  #puts 'Testing to      ' + path_to(:reports).to_s
 
 
  #compile do
  #   puts "running compile"
  #   #system "ant compile"
  #end

  #define "commons-math_adapter" do
  #end

  #define "jlinalg_adapter" do
  #end

  #define "jython" do
  #end

  #define "meditor" do
  #end

  #define "mpi" do
  #end

  #define "mpj" do
  #end

end

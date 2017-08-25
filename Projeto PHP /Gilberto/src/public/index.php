<?php
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require '../../vendor/autoload.php';

$app = new \Slim\App;

$container = $app->getContainer();
$container['logger'] = function($c) {
    $logger = new \Monolog\Logger('my_logger');
    $file_handler = new \Monolog\Handler\StreamHandler("../logs/app.log");
    $logger->pushHandler($file_handler);

    $this->logger->addInfo("Something interesting happened");
    return $logger;
};
$container['view'] = new \Slim\Views\PhpRenderer("../templates/");
//var_dump($container['logger']);


$app->get('/hello/{name}', function (Request $request, Response $response) {
    $name = $request->getAttribute('name');

    $response = $this->view->render($response, "home.phtml", ["name" => $name]);
    return $response;
});
$app->run();
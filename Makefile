IMAGE_ID := "myownradio/fake"
CONTAINER_ID := "myownradio-fake"

build:
	docker build -t $(IMAGE_ID) .

run:
	docker run --rm -it --name $(CONTAINER_ID) $(IMAGE_ID)

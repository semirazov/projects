//Toys

let toy1 = { id: 1, next: null };
let toy2 = { id: 2, next: toy1 };
let toy3 = { id: 3, next: toy2 };
let toy4 = { id: 4, next: toy3 };

let toy5 = { id: 5, next: null };

let toys = [toy4, toy5];

class ToyStorage {
  constructor(toys) {
    this.toys = toys;
    this.map = {};
    for (let toy of toys) {
      this.map[toy.id] = toy;

      let current = toy.next;
      while (current) {
        this.map[current.id] = current;
        current = current.next;
      }
    }
    console.log(this.map);
  }

  findInner(toy, id) {
    if (toy === null) {
      return null;
    }
    if (toy.id === id) {
      return toy;
    }
    return this.findInner(toy.next, id);
  }

  find(id) {
    for (let toy of this.toys) {
      const foundToy = this.findInner(toy, id);
      if (foundToy) {
        return foundToy;
      }
    }
    return null;
  }

  findFast(id) {
    return this.map[id];
  }
}

const storage = new ToyStorage(toys);

console.log(storage.findFast(6));

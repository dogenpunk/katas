class Cell
  attr_reader :alive
  attr_writer :neighbours

  def initialize probability=1
    @alive = probability > rand
  end

  def alive?
    alive
  end

  def die!
    @alive = false
  end

  def dead?
    !alive
  end

  def next!
    @alive = @alive ? (2..3) === @neighbours : 3 == @neighbours
  end

  def to_i
    alive? ? 1 : 0
  end

  def to_s
    alive? ? 'O' : '_'
  end
end

class Board
  def initialize rows=5, probability=0.2
    @cells = Array.new( rows ) {
      Array.new( rows ) { Cell.new probability } }
  end

  def next!
    @cells.each_with_index do |row, y|
      row.each_with_index do |cell, x|
        cell.neighbours = count_living neighbours( x, y )
      end
    end

    @cells.each { |row| row.each { |cell| cell.next! } }
  end

  def current
    @cells
  end

  def rows
    @cells.size
  end

  def columns
    @cells.first.size
  end

  def neighbours x, y
    [[-1, -1], [-1, 0], [-1, 1],
     [0, -1], [0, 1],
     [1, -1], [1, 0], [1, 1]].map do |point|
      @cells[(y + point.first) % rows][(x + point.last) % columns]
    end
  end

  def count_living neighbours
    neighbours.map(&:to_i).inject(:+)
  end

  def to_s
    @cells.map { |row| row.join }.join "\n"
  end
end

class Game
  def initialize size=10, probability=0.1, runs=100
    @size  = size
    @runs  = runs
    @board = Board.new( size, probability )
  end

  def play!
    (1..@runs).each do
      @board.next!
      system( 'clear' )
      puts @board
    end
  end
end


if __FILE__ == $0
  Game.new( 50, 0.1, 200 ).play!
end
